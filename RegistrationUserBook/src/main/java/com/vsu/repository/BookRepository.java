package com.vsu.repository;

import com.vsu.entity.Book;
import com.vsu.exception.DBException;
import org.jetbrains.annotations.NotNull;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookRepository {
    private static final String SELECT_BY_ID_QUERY = "SELECT * FROM book WHERE id_book = ?";
    private static final String SELECT_ALL_QUERY = "SELECT * FROM book WHERE id_profile = ?";
    private static final String INSERT_QUERY = "INSERT INTO book(author, title, year_book, id_profile) VALUES (?, ?, ?, ?)";
    private static final String DELETE_QUERY_BY_ID = "DELETE FROM book WHERE id_book = ?";
    private static final String UPDATE_QUERY = "UPDATE book SET author = ?, title = ?, year_book = ?, id_profile = ? WHERE id_book = ?";

    private final ConnectionFactory connectionFactory;

    public BookRepository(ConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }

    public Book selectById(Long id) {
        try (Connection connection = connectionFactory.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(SELECT_BY_ID_QUERY);
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return getBook(resultSet);
            }
            return null;
        } catch (SQLException e) {
            throw new DBException(e);
        }
    }

    public List<Book> selectAllByUserId(Long idUser) {
        try (Connection connection = connectionFactory.getConnection()) {
            List<Book> books = new ArrayList<>();
            PreparedStatement statement = connection.prepareStatement(SELECT_ALL_QUERY);
            statement.setLong(1, idUser);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                books.add(getBook(resultSet));
            }
            return books;
        } catch (SQLException e) {
            throw new DBException(e);
        }
    }

    public int insert(Book book) {
        int countUpdate = 0;
        try (Connection connection = connectionFactory.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(INSERT_QUERY);
            setBookParamsToStatement(book, statement);
            countUpdate = statement.executeUpdate();
        } catch (SQLException e) {
            throw new DBException(e);
        }
        return countUpdate;
    }

    public int deleteById(Long id) {
        int countUpdate = 0;
        try (Connection connection = connectionFactory.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(DELETE_QUERY_BY_ID);
            statement.setLong(1, id);
            countUpdate = statement.executeUpdate();
        } catch (SQLException e) {
            throw new DBException(e);
        }
        return countUpdate;
    }

    public int updateByID(Book book) {
        int countUpdate = 0;
        try (Connection connection = connectionFactory.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(UPDATE_QUERY);
            setBookParamsToStatement(book, statement);
            statement.setLong(5, book.getId());
            countUpdate = statement.executeUpdate();
        } catch (SQLException e) {
            throw new DBException(e);
        }
        return countUpdate;
    }

    private void setBookParamsToStatement(Book book, PreparedStatement statement) throws SQLException {
        statement.setString(1, book.getAuthor());
        statement.setString(2, book.getTitle());
        statement.setInt(3, book.getYear());
        statement.setLong(4, book.getIdProfile());
    }

    @NotNull
    private Book getBook(ResultSet resultSet) throws SQLException {
        Book book = new Book();
        book.setId(resultSet.getLong("id_book"));
        book.setAuthor(resultSet.getString("author"));
        book.setTitle(resultSet.getString("title"));
        book.setYear(resultSet.getInt("year_book"));
        book.setIdProfile(resultSet.getLong("id_profile"));
        return book;
    }
}
