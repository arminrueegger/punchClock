package ch.zli.m223.service;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import ch.zli.m223.model.Category;
import ch.zli.m223.model.Entry;
import ch.zli.m223.model.Tag;

@ApplicationScoped
public class EntryService {
    @Inject
    private EntityManager entityManager;

  @Transactional
  public Entry createEntry(Entry entry) {
    entry.setId(null);

    if (entry.getCategories() != null && entry.getCategories().getId() != null) {
      Category managedCategory = entityManager.getReference(Category.class, entry.getCategories().getId());
      entry.setCategories(managedCategory);
    } else {
      entry.setCategories(null);
    }

    if (entry.getTag() != null && entry.getTag().getId() != null) {
      Tag managedTag = entityManager.getReference(Tag.class, entry.getTag().getId());
      entry.setTag(managedTag);
    } else {
      entry.setTag(null);
    }

    entityManager.persist(entry);
    return entry;
  }

    public List<Entry> findAll() {
        var query = entityManager.createQuery("FROM Entry", Entry.class);
        return query.getResultList();
    }
  @Transactional
  public Entry updatesEntry(Entry entry) {
    entityManager.merge(entry);
    return entry;
  }

  @Transactional
  public void deleteEntry(long id) {
    entityManager.remove(entityManager.find(Entry.class, id));
  }
}
