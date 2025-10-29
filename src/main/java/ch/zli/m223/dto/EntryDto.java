package ch.zli.m223.dto;

import java.time.LocalDateTime;

import ch.zli.m223.model.Category;
import ch.zli.m223.model.Entry;
import ch.zli.m223.model.Tag;

public class EntryDto {
  private Long id;
  private LocalDateTime checkIn;
  private LocalDateTime checkOut;
  private Category category;
  private Tag tag;

  public EntryDto() {}

  public EntryDto(Long id, LocalDateTime checkIn, LocalDateTime checkOut, Category category, Tag tag) {
    this.id = id;
    this.checkIn = checkIn;
    this.checkOut = checkOut;
    this.category = category;
    this.tag = tag;
  }

  public Long getId() { return id; }
  public void setId(Long id) { this.id = id; }

  public LocalDateTime getCheckIn() { return checkIn; }
  public void setCheckIn(LocalDateTime checkIn) { this.checkIn = checkIn; }

  public LocalDateTime getCheckOut() { return checkOut; }
  public void setCheckOut(LocalDateTime checkOut) { this.checkOut = checkOut; }

  public Category getCategory() { return category; }
  public void setCategory(Category category) { this.category = category; }

  public Tag getTag() { return tag; }
  public void setTag(Tag tag) { this.tag = tag; }
  
  public Entry toDomain() {
    Entry entry = new Entry();
    entry.setCheckIn(this.checkIn);
    entry.setCheckOut(this.checkOut);
    entry.setCategories(this.category);
    entry.setTag(this.tag);
    return entry;
  }
}
