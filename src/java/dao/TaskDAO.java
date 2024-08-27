package dao;

import model.Task;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TaskDAO {
    private String jdbcURL = "jdbc:mysql://localhost:3306/dailyroutine?useSSL=false&allowPublicKeyRetrieval=true";
    private String jdbcUsername = "root";
    private String jdbcPassword = "210645";  // เปลี่ยนเป็นรหัสผ่านที่ถูกต้องของ MySQL
    private Connection jdbcConnection;

    public TaskDAO() {
    }

    protected void connect() throws SQLException {
    if (jdbcConnection == null || jdbcConnection.isClosed()) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new SQLException("Unable to load MySQL Driver", e);
        }
        jdbcConnection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
    }
}

    protected void disconnect() throws SQLException {
        if (jdbcConnection != null && !jdbcConnection.isClosed()) {
            jdbcConnection.close();
        }
    }

    public boolean insertTask(Task task) throws SQLException {
        String sql = "INSERT INTO tasks (title, description, done) VALUES (?, ?, ?)";
        connect();

        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setString(1, task.getTitle());
        statement.setString(2, task.getDescription());
        statement.setBoolean(3, task.isDone());

        boolean rowInserted = statement.executeUpdate() > 0;
        statement.close();
        disconnect();
        return rowInserted;
    }

    public List<Task> listAllTasks() throws SQLException {
        List<Task> listTask = new ArrayList<>();

        String sql = "SELECT * FROM tasks";

        connect();

        Statement statement = jdbcConnection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);

        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String title = resultSet.getString("title");
            String description = resultSet.getString("description");
            boolean done = resultSet.getBoolean("done");

            Task task = new Task();
            task.setId(id);
            task.setTitle(title);
            task.setDescription(description);
            task.setDone(done);

            listTask.add(task);
        }

        resultSet.close();
        statement.close();

        disconnect();

        return listTask;
    }

    public boolean deleteTask(int id) throws SQLException {
        String sql = "DELETE FROM tasks WHERE id = ?";

        connect();

        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setInt(1, id);

        boolean rowDeleted = statement.executeUpdate() > 0;
        statement.close();
        disconnect();
        return rowDeleted;
    }

    public boolean updateTask(Task task) throws SQLException {
        String sql = "UPDATE tasks SET title = ?, description = ?, done = ? WHERE id = ?";
        connect();

        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setString(1, task.getTitle());
        statement.setString(2, task.getDescription());
        statement.setBoolean(3, task.isDone());
        statement.setInt(4, task.getId());

        boolean rowUpdated = statement.executeUpdate() > 0;
        statement.close();
        disconnect();
        return rowUpdated;
    }

    public Task getTask(int id) throws SQLException {
        Task task = null;
        String sql = "SELECT * FROM tasks WHERE id = ?";

        connect();

        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setInt(1, id);

        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            String title = resultSet.getString("title");
            String description = resultSet.getString("description");
            boolean done = resultSet.getBoolean("done");

            task = new Task();
            task.setId(id);
            task.setTitle(title);
            task.setDescription(description);
            task.setDone(done);
        }

        resultSet.close();
        statement.close();

        return task;
    }
}
