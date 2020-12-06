import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        final double[][] xInputs = {
                {1, -2, 1.5, 0},
                {1, -0.5, -2, -1.5},
                {0, 1, -1, 1.5}
        };

        final int[] desiredOutput = {1,-1,1};
        Perceptron neuron = new Perceptron(xInputs, desiredOutput);
        System.out.println("\nFinal weights:\n" + Arrays.toString(neuron.train()));
        System.out.println("Iteration took: " + neuron.iterCount);
        //testRandomInputs(neuron);
    }

    private static void testRandomInputs(Perceptron neuron) {
        double[][] testInput = getRandomInputs(5);
        int[] result = neuron.guess(testInput);
        for(int i = 0; i < testInput[0].length; i++) {
            System.out.println(Arrays.toString(testInput[i]) + " Result: " + result[i]);
        }
    }

    private static double[][] getRandomInputs(int size) {
        double[][] testInputs = new double[size][];
        for(int i = 0; i < 5; i++) {
            double[] input = new double[4];
            for(int j = 0; j < input.length; j++) {
                input[j] = getRandomNumber(-20, 20);
            }
            testInputs[i] = input;
        }
        return testInputs;
    }

    public static double getRandomNumber(int min, int max) {
        return (double) ((int) ((Math.random() * (max - min)) + min))/10;
    }
}
