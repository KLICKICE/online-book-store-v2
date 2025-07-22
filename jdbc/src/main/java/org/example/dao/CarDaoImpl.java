package org.example.dao;

import org.example.ConnectionUtil;
import org.example.model.Car;

import java.sql.*;
import java.util.Optional;

public class CarDaoImpl implements CarDao{

    @Override
    public Car save(Car car) {
        String sql = "INSERT INTO car (model, year) VALUES (?, ?)";
        try (Connection connection = ConnectionUtil.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, car.getModel());
            statement.setInt(2, car.getYear());
            int affectedRows = statement.executeUpdate();
            if (affectedRows < 1) {
                throw new SQLException("Expected to insert at least one row, but inserted 0 rows");
            }
            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                car.setId(generatedKeys.getObject(1, Long.class));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Can't add new car: " + car, e);
        }
        return null;
    }

    @Override
    public Car get(Long id) {
        String sql = "SELECT * FROM car WHERE id = ?";
        try (Connection connection = ConnectionUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);) {
            statement.setLong(1, 1L);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String model = resultSet.getString("model");
                int year = resultSet.getObject("year", Integer.class);

                Car car = new Car();
                car.setId(id);
                car.setModel(model);
                car.setYear(year);
                return car;
            }
        } catch (SQLException e) {
            throw new RuntimeException("Can't connect to database");
        }
        return null;
    }

    @Override
    public Optional<Car> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public Car update(Car car) {
        return null;
    }

    @Override
    public boolean delete(Car car) {
        return false;
    }
}
