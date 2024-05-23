package com.jdbc.dbservice;

import com.jdbc.model.Utente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UtenteDAO {

    public void createUtente(Utente utente) {
        String sql = "INSERT INTO utente (nome, email) VALUES (?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, utente.getNome());
            pstmt.setString(2, utente.getEmail());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Utente getUtenteById(int id) {
        String sql = "SELECT * FROM utente WHERE id = ?";
        Utente utente = null;
        try (Connection conn = DatabaseConnection.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                utente = new Utente(rs.getInt("id"), rs.getString("nome"), rs.getString("email"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return utente;
    }

    public List<Utente> getAllUtenti() {
        String sql = "SELECT * FROM utente";
        List<Utente> utenti = new ArrayList<>();
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                Utente utente = new Utente(rs.getInt("id"), rs.getString("nome"), rs.getString("email"));
                utenti.add(utente);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return utenti;
    }

public void updateUtente(Utente utente) {
        String sql = "UPDATE utente SET nome = ?, email = ? WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, utente.getNome());
            pstmt.setString(2, utente.getEmail());
            pstmt.setInt(3, utente.getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteUtente(int id) {
        String sql = "DELETE FROM utente WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
