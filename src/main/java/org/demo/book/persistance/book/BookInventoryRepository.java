package org.demo.book.persistance.book;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
interface BookInventoryRepository extends JpaRepository<BookInventory, Long> {
    BookInventory getByBookId(Long bookId);
}
