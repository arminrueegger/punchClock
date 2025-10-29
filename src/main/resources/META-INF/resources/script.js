const URL = 'http://localhost:8080';
let entries = [];
let categories = [];
let tags = [];

// Datum + Uhrzeit -> ISO
const dateAndTimeToDate = (dateString, timeString) => {
  return new Date(`${dateString}T${timeString}`).toISOString();
};

// --- CREATE ---
const createEntry = (e) => {
  e.preventDefault();
  const formData = new FormData(e.target);

  const categoryId = formData.get('categoryId');
  const tagId = formData.get('tagId');

  // WICHTIG: Feldnamen müssen zum EntryDto passen (category, tag)
  const payload = {
    checkIn: dateAndTimeToDate(formData.get('checkInDate'), formData.get('checkInTime')),
    checkOut: dateAndTimeToDate(formData.get('checkOutDate'), formData.get('checkOutTime')),
    category: categoryId ? { id: Number(categoryId) } : null,
    tag:      tagId ? { id: Number(tagId) } : null,
  };

  fetch(`${URL}/entries`, {
    method: 'POST',
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify(payload)
  })
    .then(async (r) => {
      if (!r.ok) {
        const text = await r.text().catch(() => '');
        throw new Error(`POST /entries failed (${r.status}): ${text}`);
      }
      return r.json();
    })
    .then((created) => {
      entries.unshift(created); // neueste zuerst
      renderEntries();
      e.target.reset();
      document.querySelector('#categorySelect').selectedIndex = 0;
      document.querySelector('#tagSelect').selectedIndex = 0;
    })
    .catch((err) => {
      console.error(err);
      alert('Speichern fehlgeschlagen. Details in der Konsole.');
    });
};

// --- READ ---
const indexEntries = () => {
  fetch(`${URL}/entries`, { method: 'GET' })
    .then(r => r.json())
    .then(result => {
      entries = result || [];
      renderEntries();
    })
    .catch((err) => {
      console.error(err);
      entries = [];
      renderEntries();
    });
};

const loadCategories = () => {
  return fetch(`${URL}/categories`, { method: 'GET' })
    .then(r => r.json())
    .then(result => { categories = result || []; renderCategoryOptions(); })
    .catch((err) => { console.error(err); categories = []; renderCategoryOptions(); });
};

const loadTags = () => {
  return fetch(`${URL}/tags`, { method: 'GET' })
    .then(r => r.json())
    .then(result => { tags = result || []; renderTagOptions(); })
    .catch((err) => { console.error(err); tags = []; renderTagOptions(); });
};

// --- Dropdowns rendern ---
const renderCategoryOptions = () => {
  const select = document.querySelector('#categorySelect');
  select.length = 1; // Platzhalter behalten
  categories.forEach(c => {
    const opt = document.createElement('option');
    opt.value = c.id;
    opt.textContent = c.name;
    select.appendChild(opt);
  });
};

const renderTagOptions = () => {
  const select = document.querySelector('#tagSelect');
  select.length = 1; // Platzhalter behalten
  tags.forEach(t => {
    const opt = document.createElement('option');
    opt.value = t.id;
    opt.textContent = t.title;
    select.appendChild(opt);
  });
};

// --- DELETE ---
const deleteEntry = (id) => {
  if (!confirm(`Eintrag #${id} wirklich löschen?`)) return;

  fetch(`${URL}/entries/${id}`, { method: 'DELETE' })
    .then((res) => {
      if (!res.ok && res.status !== 204) {
        throw new Error(`Delete failed (status ${res.status})`);
      }
      entries = entries.filter(e => e.id !== id);
      renderEntries();
    })
    .catch((err) => {
      console.error(err);
      alert('Löschen fehlgeschlagen. Details in der Konsole.');
    });
};

// --- Helpers ---
const createCell = (text) => {
  const cell = document.createElement('td');
  cell.innerText = text;
  return cell;
};

const createActionCell = (entry) => {
  const cell = document.createElement('td');
  const btn = document.createElement('button');
  btn.type = 'button';
  btn.textContent = 'Löschen';
  btn.addEventListener('click', () => deleteEntry(entry.id));
  cell.appendChild(btn);
  return cell;
};

// --- Tabelle rendern ---
const renderEntries = () => {
  const display = document.querySelector('#entryDisplay');
  display.innerHTML = '';

  if (!entries || entries.length === 0) {
    const row = document.createElement('tr');
    const cell = document.createElement('td');
    cell.colSpan = 6;
    cell.innerText = 'Keine Einträge vorhanden';
    row.appendChild(cell);
    display.appendChild(row);
    return;
  }

  entries.forEach((entry) => {
    const row = document.createElement('tr');
    row.appendChild(createCell(entry.id));
    row.appendChild(createCell(new Date(entry.checkIn).toLocaleString()));
    row.appendChild(createCell(new Date(entry.checkOut).toLocaleString()));
    const catText = entry.category?.name || entry.categories?.name || '-';
    const tagText = entry.tag?.title || '-';
    row.appendChild(createCell(catText));
    row.appendChild(createCell(tagText));
    row.appendChild(createActionCell(entry));
    display.appendChild(row);
  });
};

// --- Init ---
document.addEventListener('DOMContentLoaded', function () {
  const createEntryForm = document.querySelector('#createEntryForm');
  createEntryForm.addEventListener('submit', createEntry);
  Promise.all([loadCategories(), loadTags()]).then(() => {
    indexEntries();
  });
});
