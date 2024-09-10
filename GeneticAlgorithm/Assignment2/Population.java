// Brock Polnik
// Assignment2
import java.io.IOException;
import java.util.Random;

public class Population {

    private final Candidate[] population; // Representation of the current population
    private final double crossoverProbability = 0.5; // Probability of crossover
    private final double mutationProbability = 0.001; // Probability of mutation
    private final int populationSize = 100; // Population size
    private double totalFitness;
    private final BlackBoxFunctionInterface fitnessFunction;
    private final Random rand = new Random();

    public Population() {
        fitnessFunction = new BlackBoxFunction(); // Generate a blackbox function to calculate fitness
        population = initializePopulation();
        totalFitness = calculateTotalFitness();
    }

    private Candidate[] initializePopulation() {
        Candidate[] initialPopulation = new Candidate[populationSize];
        for (int i = 0; i < populationSize; i++) {
            initialPopulation[i] = new Candidate(fitnessFunction);
        }
        return initialPopulation;
    }

    public Candidate[] getPopulation() {
        return population;
    }

    public double getTotalFitness() {
        return totalFitness;
    }

    public int getSize() {
        return populationSize;
    }

    public double calculateTotalFitness() {
        double fitnessSum = 0;
        for (Candidate candidate : population) {
            fitnessSum += candidate.getFitness();
        }
        return fitnessSum;
    }

    public double getHighestFitness() {
        double highest = Double.MIN_VALUE;
        for (Candidate candidate : population) {
            if (candidate.getFitness() > highest) {
                highest = candidate.getFitness();
            }
        }
        return highest;
    }

    public double getAverageFitness() {
        return totalFitness / populationSize;
    }

    public int rouletteWheelSelection() {
        // Selects a candidate from the population pool using roulette wheel selection
        double selectionPoint = rand.nextDouble() * totalFitness;
        double cumulativeFitness = 0;
        for (int i = 0; i < populationSize; i++) {
            cumulativeFitness += population[i].getFitness();
            if (cumulativeFitness >= selectionPoint) {
                return i;
            }
        }
        return populationSize - 1; // Should not reach here unless due to rounding errors
    }

    public void generateNextGeneration() {
        // Emulate the creation of a new generation by replacing all existing parents
        Candidate[] newGeneration = new Candidate[populationSize];
        int index = 0;

        while (index < populationSize) {
            int parent1Index = rouletteWheelSelection();
            int parent2Index;
            do {
                parent2Index = rouletteWheelSelection();
            } while (parent1Index == parent2Index); // Ensure a parent does not breed with itself

            Candidate parent1 = population[parent1Index];
            Candidate parent2 = population[parent2Index];

            Candidate[] offspring;
            if (rand.nextDouble() < crossoverProbability) {
                offspring = parent1.crossover(parent2);
            } else {
                offspring = new Candidate[]{parent1, parent2}; // Copy parents if no crossover
            }

            // Mutate offspring
            offspring[0].mutate(mutationProbability);
            offspring[1].mutate(mutationProbability);

            // Add offspring to the new generation
            newGeneration[index++] = offspring[0];
            if (index < populationSize) {
                newGeneration[index++] = offspring[1];
            }
        }

        // Replace the old population with the new generation
        System.arraycopy(newGeneration, 0, population, 0, populationSize);
        totalFitness = calculateTotalFitness();
    }

    public static void main(String[] args) {
        int numGenerations = 50000;
        Population population = new Population();

        for (int i = 0; i < numGenerations; i++) {
            System.out.println("This is generation " + (i + 1));
            System.out.println("Average fitness for generation " + (i + 1) + ": " + population.getAverageFitness());
            System.out.println("Highest fitness for generation " + (i + 1) + ": " + population.getHighestFitness());

            population.generateNextGeneration();
        }
    }
}
