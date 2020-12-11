import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class SimulationResult {
    List<Person> population;
    LinkedHashMap<Person, List<Person>> contact_tracingList;
    int total;
    int counter = 0; // counter for contact tracing
    public SimulationResult(List<Person> population, LinkedHashMap<Person, List<Person>> contact_tracingList){
        this.population = population;
        this.total = getTotalPopulation(this.population);
        this.contact_tracingList = contact_tracingList;
    }

    public void printHeaderResult(){
        System.out.println("Total population: " + getTotalPopulation(this.population));
        System.out.println("Total wearing mask : " + getPopulationWithMask(this.population));
        System.out.println("Total following quarantine: " + getQuarantineCount(this.population));
        System.out.println("Total population following social distancing" + getPopulationFoll_SocialDistancing(this.population));
        System.out.format("%10s%10s%15s%30s%n", "Total", "Healthy", "Infected", "Infected & Quarantined");
        System.out.format("============================================================================================%n");
    }
    public void printSimulationResult(){
        counter++;
        System.out.format("%10s%10s%15s%30s%n", total, getHealthyCount(this.population), getTotalInfected(this.population), getInfectedQuarantine(this.population) );

        // printing contract tracing after 300 simulation
        if(counter % 300 == 0){
            printContactTracing(this.contact_tracingList);
        }
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

    public void printContactTracing(LinkedHashMap<Person, List<Person>> contact_graph){
        for(Map.Entry<Person,List<Person>> entry : contact_graph.entrySet()){
            Person source = entry.getKey();
            List<Person> contactList = entry.getValue();

            System.out.print("Person " + source.getId() + " was infected and contracted to --> ");
            for(Person p: contactList){
                System.out.print("  "  + p.getId());
            }
            System.out.println();
        }
    }
}
