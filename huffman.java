import java.util.*;

class HuffmanNode {
    int data;
    char c;
    HuffmanNode left, right;
}

class MyComparator implements Comparator<HuffmanNode> {
    public int compare(HuffmanNode x, HuffmanNode y) {
        return x.data - y.data;
    }
}

public class HuffmanEncoding {
    public static void printCode(HuffmanNode root, String s) {
        if (root.left == null && root.right == null && Character.isLetter(root.c)) {
            System.out.println(root.c + " : " + s);
            return;
        }
        printCode(root.left, s + "0");
        printCode(root.right, s + "1");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter number of characters: ");
        int n = scanner.nextInt();

        char[] charArray = new char[n];
        int[] charFreq = new int[n];

        System.out.println("Enter characters and their frequencies:");
        for (int i = 0; i < n; i++) {
            charArray[i] = scanner.next().charAt(0);
            charFreq[i] = scanner.nextInt();
        }

        PriorityQueue<HuffmanNode> q = new PriorityQueue<>(n, new MyComparator());
        
        for (int i = 0; i < n; i++) {
            HuffmanNode hn = new HuffmanNode();
            hn.c = charArray[i];
            hn.data = charFreq[i];
            hn.left = null;
            hn.right = null;
            q.add(hn);
        }
        
        HuffmanNode root = null;
        
        while (q.size() > 1) {
            HuffmanNode x = q.poll();
            HuffmanNode y = q.poll();
            
            HuffmanNode f = new HuffmanNode();
            f.data = x.data + y.data;
            f.c = '-';
            f.left = x;
            f.right = y;
            
            root = f;
            q.add(f);
        }
        
        System.out.println("Huffman Codes are:");
        printCode(root, "");
        
        scanner.close();
    }
}
