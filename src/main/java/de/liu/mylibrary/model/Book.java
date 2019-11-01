package de.liu.mylibrary.model;

import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "books")
public class Book extends BaseEntity {

	@Column(name = "title")
	@NotEmpty
	private String title;

	@Column(name = "author")
	@NotEmpty
	private String author;

	@Column(name = "type")
	@Enumerated(value = EnumType.STRING)
	private BookType type;

	@Column(name = "status")
	@Enumerated(value = EnumType.STRING)
	private Status status;

	@OneToMany(mappedBy = "book", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Set<BorrowHistory> borrowHistories;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public BookType getType() {
		return type;
	}

	public void setType(BookType type) {
		this.type = type;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Set<BorrowHistory> getBorrowHistories() {
		return borrowHistories;
	}

	public void setBorrowHistories(Set<BorrowHistory> borrowHistories) {
		this.borrowHistories = borrowHistories;
	}

}
