package se.lexicon.data.impl;

import se.lexicon.data.CalendarDao;
import se.lexicon.data.db.MeetingCalendarDBConnection;
import se.lexicon.data.util.Querries;
import se.lexicon.model.Calendar;
import se.lexicon.model.Meeting;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

public class CalendarDaoImpl implements CalendarDao {

    //todo Implement methods
    @Override
    public Calendar createCalendar(String title, String username)
    {
        Calendar newCalendar = null;
        try(
                Connection connection = MeetingCalendarDBConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(Querries.CREATE_CALENDAR,
                        Statement.RETURN_GENERATED_KEYS)
                ){
            statement.setString(1,title);
            statement.setString(2,username);
            statement.executeUpdate();
            try(
                    ResultSet resultSet = statement.getGeneratedKeys()
            ){
                int calendarId = 0;
                String newTitle = null;
                String newUser = null;
                List<Meeting> newList = new ArrayList<>();
                while (resultSet.next()) {
                    calendarId = resultSet.getInt(1);
                    newTitle = resultSet.getString(2);
                    newUser = resultSet.getString(3);
                }
                newCalendar = new Calendar(
                        calendarId,
                        newTitle,
                        newUser,
                        newList);
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return newCalendar;
    }



    @Override
    public Optional<Calendar> findById(int id) {
        return Optional.empty();
    }

    @Override
    public Collection<Calendar> findCalendarsByUsername(String username) {
        return null;
    }

    @Override
    public Optional<Calendar> findByTitle(String title) {
        return Optional.empty();
    }

    @Override
    public boolean deleteCalendar(int id) {
        return false;
    }
}
