
package project.futuretraffic;

class BusNotFoundException extends RuntimeException {

  BusNotFoundException(Integer id) {
    super("Could not find bus " + id);
  }
}