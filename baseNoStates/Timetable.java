package baseNoStates;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.DayOfWeek;
import java.util.ArrayList;


/*
Class Schedule that assigns each user group to control the accesses to the building.
It creates an instance for every existing group, and contains the initial date, the final one,
days of access and also the hours.
*/
public class Timetable {
  private final LocalDate startDate;
  private final LocalDate endDate;
  private final ArrayList<DayOfWeek> days;
  private final LocalTime startTime;
  private final LocalTime endTime;

  public Timetable(LocalDate startDate, LocalDate endDate, ArrayList<DayOfWeek> days,
                   LocalTime startTime, LocalTime endTime) {
    this.startDate = startDate;
    this.endDate = endDate;
    this.days = days;
    this.startTime = startTime;
    this.endTime = endTime;
  }

  public LocalDate getStartDate() {
    return startDate;
  }

  public LocalDate getEndDate() {
    return endDate;
  }

  public ArrayList<DayOfWeek> getDays() {
    return days;
  }

  public LocalTime getStartTime() {
    return startTime;
  }

  public LocalTime getEndTime() {
    return endTime;
  }
}
