package console;

import java.io.*;
import java.util.concurrent.Callable;

/**
 * A callable task for retrieving user input
 *
 * @version 1.2
 */
public class ConsoleInputTask implements Callable<String> {
  public String call() throws IOException {
    // Get the input reader
    var reader = new BufferedReader(new InputStreamReader(System.in));
    
    try {
      String input;
      do {
        try {
          // While there is no data to read..
          while (!reader.ready()) {
            // Wait
            //noinspection BusyWait
            Thread.sleep(200);
          }
          
          // Read the user-input
          input = reader.readLine();
        }
        // If the input was interrupted..
        catch (InterruptedException e) {
          // Return nothing
          return null;
        }
      }
      // Repeat until the input is not empty
      while (input.equals(""));
      
      // Return the retrieved user-input
      return input;
    } finally {
      reader.close();
    }
  }
}