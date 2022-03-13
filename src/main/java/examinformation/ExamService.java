package examinformation;

import java.io.IOException;
import java.nio.file.Path;
import java.util.*;

public class ExamService {

    private Map<String, ExamResult> results = new TreeMap<>();
    private int theoryMax;
    private int practiceMax;

    public Map<String, ExamResult> getResults() {
        return results;
    }

    public int getTheoryMax() {
        return theoryMax;
    }

    public int getPracticeMax() {
        return practiceMax;
    }

    public void readFromFIle(Path path) {
        try (Scanner scanner = new Scanner(path)){
            String[] maxParts = scanner.nextLine().split(" ");
            theoryMax = Integer.parseInt(maxParts[0]);
            practiceMax = Integer.parseInt(maxParts[1]);
            while (scanner.hasNext()) {
                putResults(scanner.nextLine());
            }
        } catch (IOException ioe) {
            throw new IllegalArgumentException("Cannot read file: " + path);
        }
    }

    private void putResults(String line) {
        String[] parts = line.split(";");
        String name = parts[0];
        String[] pointParts = parts[1].split(" ");
        int theory = Integer.parseInt(pointParts[0]);
        int practice = Integer.parseInt(pointParts[1]);
        results.put(name, new ExamResult(theory, practice));
    }

    public List<String> findPeopleFailed() {
        return results.keySet().stream().filter(name -> results.get(name).getTheory() < theoryMax * 0.51 || results.get(name).getPractice() < practiceMax * 0.51).toList();
    }

    public String findBestPerson() {
        return results.keySet().stream().filter(name -> !findPeopleFailed().contains(name)).max(Comparator.comparing(o -> results.get(o).getTotal())).orElseThrow(()->new IllegalStateException("Result not found!"));
    }
}
