// Brock Polnik
// Assignment2

import java.util.Arrays;
import java.util.Random;

public class Candidate {

    private double fitness; // Fitness for this candidate
    private final boolean[] chromosome; // The candidate's chromosome
    private final Random rand = new Random();
    private final BlackBoxFunctionInterface bbFunction;

    public Candidate(BlackBoxFunctionInterface bb) {
        this.bbFunction = bb;
        this.chromosome = new boolean[bb.getLength()];
        initializeChromosome();
        this.fitness = calculateFitness();
    }

    private void initializeChromosome() {
        for (int i = 0; i < chromosome.length; i++) {
            chromosome[i] = rand.nextBoolean(); // Simplified random boolean initialization
        }
    }

    public boolean[] getChromosome() {
        return chromosome;
    }

    public double getFitness() {
        return fitness;
    }

    public int getSize() {
        return chromosome.length; // Size is the chromosome length
    }

    public double calculateFitness() {
        this.fitness = bbFunction.function(chromosome);
        return this.fitness;
    }

    public Candidate[] crossover(Candidate otherParent) {
        // Uniform crossover
        Candidate[] offspring = {new Candidate(this.bbFunction), new Candidate(otherParent.bbFunction)};
        for (int i = 0; i < chromosome.length; i++) {
            if (rand.nextBoolean()) {
                offspring[0].chromosome[i] = otherParent.chromosome[i];
                offspring[1].chromosome[i] = this.chromosome[i];
            } else {
                offspring[0].chromosome[i] = this.chromosome[i];
                offspring[1].chromosome[i] = otherParent.chromosome[i];
            }
        }
        offspring[0].calculateFitness();
        offspring[1].calculateFitness();
        return offspring;
    }

    public Candidate mutate(double mutationProbability) {
        for (int i = 0; i < chromosome.length; i++) {
            if (rand.nextDouble() < mutationProbability) {
                chromosome[i] = !chromosome[i]; // Flip the gene
            }
        }
        calculateFitness(); // Recalculate fitness after mutation
        return this;
    }

    @Override
    public String toString() {
        return Arrays.toString(chromosome);
    }

    /* For testing purposes
    public static void main(String[] args) {
        BlackBoxFunctionInterface bbFunction = new BlackBoxFunction(); // Example black box function initialization
        for (int g = 0; g < 11; g++) { 
            System.out.println("This is Generation " + (g + 1));

            Candidate p1 = new Candidate(bbFunction);
            Candidate p2 = new Candidate(bbFunction);

            Candidate[] children = p1.crossover(p2);
            Candidate c1 = children[0];
            Candidate c2 = children[1];

            System.out.println("Sizes");
            System.out.println(p1.getSize());
            System.out.println(p2.getSize());
            System.out.println(c1.getSize());
            System.out.println(c2.getSize());

            System.out.println("Fitness");
            System.out.println(p1.getFitness());
            System.out.println(p2.getFitness());
            System.out.println(c1.getFitness());
            System.out.println(c2.getFitness());
        }
    }
    */
}
