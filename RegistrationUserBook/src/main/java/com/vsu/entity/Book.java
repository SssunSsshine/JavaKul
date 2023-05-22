package com.vsu.entity;

import java.util.Objects;

public class Book {
    private Long id;
    private String author;
    private String title;
    private Integer year;
    private Long idProfile;


    public Book(String author, String title, Integer year, Long idProfile) {
        this.author = author;
        this.title = title;
        this.year = year;
        this.idProfile = idProfile;
    }

    public Book() {
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getIdProfile() {
        return idProfile;
    }

    public void setIdProfile(Long idProfile) {
        this.idProfile = idProfile;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return Objects.equals(getId(), book.getId()) && Objects.equals(getAuthor(), book.getAuthor()) && Objects.equals(getTitle(), book.getTitle()) && Objects.equals(getYear(), book.getYear()) && Objects.equals(getIdProfile(), book.getIdProfile());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getAuthor(), getTitle(), getYear(), getIdProfile());
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", author='" + author + '\'' +
                ", title='" + title + '\'' +
                ", year=" + year +
                ", idProfile=" + idProfile +
                '}';
    }
}
