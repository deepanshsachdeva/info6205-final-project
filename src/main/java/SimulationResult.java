import java.util.List;

public class SimulationResult {
    List<Person> population;
    int total;
    public SimulationResult(List<Person> population){
        this.population = population;
        this.total = getTotalPopulation(this.population);
        System.out.println("Total population: " + getTotalPopulation(this.population));
        System.out.println("Total wearing mask : " + getPopulationWithMask(this.population));
        System.out.println("Total following quarantine: " + getQuarantineCount(this.population));
        System.out.println("Total population following social distancing" + getPopulationFoll_SocialDistancing(this.population));
        System.out.format("%10s%10s%15s%30s%n", "Total", "Healthy", "Infected", "Infected & Quarantined");
        System.out.format("============================================================================================%n");

    }

    public void printSimulationResult(){
        System.out.format("%10s%10s%15s%30s%n", total, getHealthyCount(this.population), getTotalInfected(this.population), getInfectedQuarantine(this.population) );
    }

    public int getTotalPopulation(List<Person> population){
        return population.size();
    }

    public int getHealthyCount(List<Person> population){
        int counter = 0;
        for(Person p: population){
            if(!p.isInfected())
                counter++;
        }
        return counter;
    }
    public int getTotalInfected(List<Person> population){
        int counter = 0;
        for(Person p: population){
            if(p.isInfected())
                counter++;
        }
        return counter;
    }

    public int getQuarantineCount(List<Person> population){
        int counter = 0;
        for(Person p: population){
            if(p.isFoll_quarantine())
                counter++;
        }
        return counter;
    }

    public int getPopulationWithMask(List<Person> population){
        int counter = 0;
        for(Person p: population){
            if(p.isWearing_mask())
                counter++;
        }
        return counter;
    }

    public int getPopulationFoll_SocialDistancing(List<Person> population){
        int counter = 0;
        for(Person p: population){
            if(p.isFoll_socialDistancing())
                counter++;
        }
        return counter;
    }

    public int getInfectedQuarantine(List<Person> population){
        int counter = 0;
        for(Person p: population){
            if(p.isInfected() && p.isFoll_quarantine())
                counter++;
        }
        return counter;
    }


}
