package com.mobiquity.packer;

import com.mobiquity.exception.APIException;
import com.mobiquity.model.Configuration;
import com.mobiquity.model.Solution;
import com.mobiquity.solver.Strategy;
import com.mobiquity.utils.ConfigurationParser;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class Packer {

  private static final Strategy strategy = new Strategy();

  public static String pack(String filePath) throws APIException {

    Path path = Paths.get(filePath);
    StringBuilder sb = new StringBuilder();
    try {
      List<String> lines = Files.lines(path).collect(Collectors.toList());

      for ( String l : lines ) {
        sb.append(Packer.process(l)).append("\n");
      }

    } catch (IOException e) {
      e.printStackTrace();
    }

    return sb.toString();
  }

  /**
   * @param line a single test case in the [capacity] : ([index],[weight],[€value])
   * @return items indexes, or '-' if there's no solution
   * @throws APIException
   */
  public static String process(String line) throws APIException {

    Configuration configuration = ConfigurationParser.parse(line);

    if ( configuration == null ) {
      throw new APIException("Invalid input file");
    }

    Solution solution = strategy.solve(configuration.getCapacity(), configuration.getItems());

    String solutionItemsList;

    if ( solution.getItems()!=null && !solution.getItems().isEmpty() ) {
      solutionItemsList = solution.getItems().stream().map( i-> i.getIndex().toString() ).collect(Collectors.joining(","));
    } else {
      solutionItemsList = "-";
    }

    return solutionItemsList;
  }
}
