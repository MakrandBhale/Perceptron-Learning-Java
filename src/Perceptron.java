import java.util.Arrays;

public class Perceptron {
    private final int SIZE = 4;
    private final double[] weights = {1, -1, 0, 0.5};
    private final int LEARNING_RATE = 1;
    private final int MAX_EPOCH = 100;
    public int iterCount = 0;
    private int[] output;

    private final double[][] xInputs;
    private final int[] desiredOutput;
    Perceptron(double[][] xInputs, int[] desiredOutput) {
        this.xInputs = xInputs;
        output = new int[this.xInputs.length];
        this.desiredOutput = desiredOutput;
    }

    public double[] train() {
        double[] wDelta = new double[SIZE];
        iterCount = 0;
        while (!isErrorZero(output) && iterCount < MAX_EPOCH) {
            iterCount++;
            output = guess(xInputs);
            printIterationInfo();

            for (int i = 0; i < xInputs.length; i++) {
                double[] xi = xInputs[i];
                int ok = output[i];
                int dk = desiredOutput[i];
                double delta = (dk - ok) * LEARNING_RATE;
                for (int j = 0; j < xi.length; j++) {
                    wDelta[j] = delta * xi[j];
                    weights[j] = weights[j] + wDelta[j];
                }
            }
        }
        return weights;
    }



    public int[] guess(double[][] inputs) {
        double sum = 0;
        final int[] output = new int[inputs.length];

        for (int j = 0; j < inputs.length; j++) {
            double[] xi = inputs[j];
            for (int i = 0; i < xi.length; i++) {
                sum = sum + (xi[i] * weights[i]);
            }
            output[j] = sgn(sum);
        }
        return output;
    }

    private int sgn(double n) {
        if (n >= 0) {
            return 1;
        }
        return -1;
    }

    private boolean isErrorZero(int[] output) {
        if(output == null) return false;
        for (int i = 0; i < output.length; i++) {
            if (desiredOutput[i] - output[i] != 0) return false;
        }
        return true;
    }

    private void printIterationInfo() {
        print("\nIteration: " + this.iterCount);
        print("Output: ", this.output);
        print("Desired Output: ", this.desiredOutput);
        print("Weights: " + Arrays.toString(this.weights));
        print("------");
    }

    private void print(String message, int[] arr) {
        print(message + Arrays.toString(arr));
    }

    private void print(String message) {
        System.out.println(message);
    }
}
