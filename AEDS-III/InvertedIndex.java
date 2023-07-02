import java.util.*;

class TrieNode {
    Map<Character, TrieNode> children;
    List<WordLocation> wordLocations;

    public TrieNode() {
        children = new HashMap<>();
        wordLocations = new ArrayList<>();
    }
}

class WordLocation {
    String word;
    List<Integer> lines;
    List<Integer> columns;

    public WordLocation(String word, int line, int column) {
        this.word = word;
        lines = new ArrayList<>();
        columns = new ArrayList<>();
        lines.add(line);
        columns.add(column);
    }

    public void addLocation(int line, int column) {
        lines.add(line);
        columns.add(column);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(word).append(": ");
        for (int i = 0; i < lines.size(); i++) {
            sb.append("{").append(lines.get(i)).append(",").append(columns.get(i)).append("} ");
        }
        return sb.toString();
    }
}

class Trie {
    private TrieNode root;

    public Trie() {
        root = new TrieNode();
    }

    public void insert(String word, int line, int column) {
        TrieNode current = root;
        for (char ch : word.toCharArray()) {
            current.children.putIfAbsent(ch, new TrieNode());
            current = current.children.get(ch);
        }
        WordLocation wordLocation = findWordLocation(current.wordLocations, word);
        if (wordLocation != null) {
            wordLocation.addLocation(line, column);
        } else {
            current.wordLocations.add(new WordLocation(word, line, column));
        }
    }

    public List<String> search(String prefix) {
        List<String> result = new ArrayList<>();
        TrieNode current = root;
        for (char ch : prefix.toCharArray()) {
            if (!current.children.containsKey(ch)) {
                return result;
            }
            current = current.children.get(ch);
        }
        traverse(current, result);
        return result;
    }

    private void traverse(TrieNode node, List<String> result) {
        if (node.wordLocations.size() > 0) {
            for (WordLocation wordLocation : node.wordLocations) {
                result.add(wordLocation.toString());
            }
        }
        for (TrieNode child : node.children.values()) {
            traverse(child, result);
        }
    }

    private WordLocation findWordLocation(List<WordLocation> wordLocations, String word) {
        for (WordLocation wordLocation : wordLocations) {
            if (wordLocation.word.equals(word)) {
                return wordLocation;
            }
        }
        return null;
    }
}

public class InvertedIndex {
    public static void main(String[] args) {
        Trie trie = new Trie();

        // Exemplo de inclusão de palavras com suas localizações
        trie.insert("apple", 1, 2);
        trie.insert("apple", 4, 5);
        trie.insert("banana", 2, 3);
        trie.insert("orange", 3, 4);
        trie.insert("orange", 6, 7);
        trie.insert("orange", 8, 9);

        // Exemplo de pesquisa por prefixo
        List<String> searchResult = trie.search("or");
        for (String result : searchResult) {
            System.out.println(result);
        }
    }
}