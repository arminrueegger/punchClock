package ch.zli.m223.model;

import javax.persistence.*;

import ch.zli.m223.dto.EntryDto;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Entry {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Schema(readOnly = true)
  private Long id;

  @Column(nullable = false)
  private LocalDateTime checkIn;

  @Column(nullable = false)
  private LocalDateTime checkOut;

  @ManyToOne
  @JoinColumn(name = "categories_id")
  private Category categories;

  @ManyToOne
  @JoinColumn(name = "tag_id")
  private Tag tag;

  public Tag getTag() {
    return tag;
  }

  public void setTag(Tag tag) {
    this.tag = tag;
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

  public Category getCategories() {return categories;}
  public void setCategories(Category categories) {this.categories = categories;}

  public EntryDto toDto() {
    EntryDto dto = new EntryDto();
    dto.setId(this.id);
    dto.setCheckIn(this.checkIn);
    dto.setCheckOut(this.checkOut);
    dto.setCategory(this.categories);
    dto.setTag(this.tag);
    return dto;
  }
}
