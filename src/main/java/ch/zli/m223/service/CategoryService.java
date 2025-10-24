package ch.zli.m223.service;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import ch.zli.m223.model.Category;

@ApplicationScoped
public class CategoryService {
  @Inject
  EntityManager entityManager;

  @Transactional
  public Category createCategory(Category category) {
    entityManager.persist(category);
    return category;
  }

  public List<Category> findAll() {
    var query = entityManager.createQuery("FROM Category", Category.class);
    return query.getResultList();
  }

  @Transactional
  public Category updateCategory(Category category) {
    return entityManager.merge(category);
  }

  @Transactional
  public void deleteCategory(long id) {
    Category managed = entityManager.find(Category.class, id);
    if (managed != null) {
      entityManager.remove(managed);
    }
  }
}
