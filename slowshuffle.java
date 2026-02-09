import java.util.HashMap;

public class slowshuffle {
    
    
    public static int[] slowshuffle(int N) {
        int[] shuffled = new int[N + 1];  // Index 1 to N
        boolean[] isNotPresent = new boolean[N + 1];
        
        
        for (int i = 1; i <= N; i++) {
            isNotPresent[i] = true;
        }
        
        int i = 0;
        while (i < N - 1) {  
            int r = 1 + (int)(Math.random() * N);
            
            if (isNotPresent[r]) {
                i++;
                shuffled[i] = r;
                isNotPresent[r] = false;
            }
            
        }
        

        for (int j = 1; j <= N; j++) {
            if (isNotPresent[j]) {
                shuffled[N] = j;
                break;
            }
        }
        
        return shuffled;
    }
    
    // Part (b)
    public static int[] biasedshuffle(int N) {
        int[] shuffled = new int[N + 1];
        
    
        for (int i = 1; i <= N; i++) {
            shuffled[i] = i;
        }
        
    
        for (int i = 1; i <= N; i++) {
            int r = 1 + (int)(Math.random() * N);  
            
            
            int temp = shuffled[r];
            shuffled[r] = shuffled[i];
            shuffled[i] = temp;
        }
        
        return shuffled;
    }
    
    
    public static int[] shuffle(int N) {
        int[] B = new int[N];
        
        
        for (int i = 0; i < N; i++) {
            B[i] = i + 1;
        }
        

        for (int b = 0; b < N - 1; b++) {
        
            int r = b + (int)(Math.random() * (N - b));
            
            
            int temp = B[b];
            B[b] = B[r];
            B[r] = temp;
        }
        
        return B;
    }
    
    // Part (c)
    public static void testShuffles() {
        int N = 3;
        int trials = 60000;
        
        HashMap<String, Integer> biasedCounts = new HashMap<>();
        HashMap<String, Integer> unbiasedCounts = new HashMap<>();
        
        for (int trial = 0; trial < trials; trial++) {
            // Test biased shuffle
            int[] biasedResult = biasedshuffle(N);
            String biasedKey = "" + biasedResult[1] + biasedResult[2] + biasedResult[3];
            if (!biasedCounts.containsKey(biasedKey)) {
                biasedCounts.put(biasedKey, 0);
            }
            biasedCounts.put(biasedKey, biasedCounts.get(biasedKey) + 1);
            
            // Test unbiased shuffle
            int[] unbiasedResult = shuffle(N);
            String unbiasedKey = "" + unbiasedResult[0] + unbiasedResult[1] + unbiasedResult[2];
            if (!unbiasedCounts.containsKey(unbiasedKey)) {
                unbiasedCounts.put(unbiasedKey, 0);
            }
            unbiasedCounts.put(unbiasedKey, unbiasedCounts.get(unbiasedKey) + 1);
        }
        
        // Print results
        System.out.println("=== BIASED SHUFFLE (60,000 trials) ===");
        System.out.println("Expected ~10,000 each if unbiased:");
        for (String key : biasedCounts.keySet()) {
            System.out.println(key + ": " + biasedCounts.get(key));
        }
        
        System.out.println("\n=== UNBIASED SHUFFLE (60,000 trials) ===");
        System.out.println("Expected ~10,000 each if unbiased:");
        for (String key : unbiasedCounts.keySet()) {
            System.out.println(key + ": " + unbiasedCounts.get(key));
        }
    }
    
    public static void main(String[] args) {
        int N = 3;
        
        
        System.out.println("Testing slowshuffle:");
        int[] slow = slowshuffle(N);
        for (int i = 1; i <= N; i++) {
            System.out.print(slow[i] + " ");
        }
        System.out.println();
        
        System.out.println("\nTesting biasedshuffle:");
        int[] biased = biasedshuffle(N);
        for (int i = 1; i <= N; i++) {
            System.out.print(biased[i] + " ");
        }
        System.out.println();
        
        System.out.println("\nTesting unbiased shuffle:");
        int[] unbiased = shuffle(N);
        for (int i = 0; i < N; i++) {
            System.out.print(unbiased[i] + " ");
        }
        System.out.println();
        
        // Run the bias test
        System.out.println("\n\nRunning 60,000 trials to test for bias...");
        testShuffles();
    }
}
