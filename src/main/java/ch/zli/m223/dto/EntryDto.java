package ch.zli.m223.dto;

import java.time.LocalDateTime;
import ch.zli.m223.model.Entry;

public class EntryDto {
  private Long id;
  private LocalDateTime checkIn;
  private LocalDateTime checkOut;

  public EntryDto() {}

  public EntryDto(Long id, LocalDateTime checkIn, LocalDateTime checkOut) {
    this.id = id;
    this.checkIn = checkIn;
    this.checkOut = checkOut;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public LocalDateTime getCheckIn() {
    return checkIn;
  }

  public void setCheckIn(LocalDateTime checkIn) {
    this.checkIn = checkIn;
  }

  public LocalDateTime getCheckOut() {
    return checkOut;
  }

  public void setCheckOut(LocalDateTime checkOut) {
    this.checkOut = checkOut;
  }
  public Entry toDomain() {
    Entry entry = new Entry();
    entry.setId(this.id);
    entry.setCheckIn(this.checkIn);
    entry.setCheckOut(this.checkOut);
    return entry;
  }
}
