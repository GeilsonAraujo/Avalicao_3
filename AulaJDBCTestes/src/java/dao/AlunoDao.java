/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.Aluno;
import util.DbUtil;

public class AlunoDao {

    private Connection connection;

    public AlunoDao() {
        connection = DbUtil.getConnection();
    }

    public void addAluno(Aluno aluno) {
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("INSERT INTO alunos (nome, nascimento, endereco) values (?, ?, ?)");
            // Parameters start with 1
            preparedStatement.setString(1, aluno.getNome());
            preparedStatement.setDate(2, new java.sql.Date(aluno.getNascimento().getTime()));
            preparedStatement.setString(3, aluno.getEndereco());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteAluno(int matricula) {
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("DELETE FROM alunos WHERE matricula=?");
            // Parameters start with 1
            preparedStatement.setInt(1, matricula);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateAluno(Aluno aluno) {
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("UPDATE alunos SET nome=?, nascimento=?, endereco=? WHERE matricula=?");
            // Parameters start with 1
            preparedStatement.setString(1, aluno.getNome());
            preparedStatement.setDate(2, new java.sql.Date(aluno.getNascimento().getTime()));
            preparedStatement.setString(3, aluno.getEndereco());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Aluno> getAllAlunos() {
        List<Aluno> listaDeAluno = new ArrayList<Aluno>();
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM alunos");
            while (rs.next()) {
                Aluno aluno = new Aluno();
                aluno.setMatricula(rs.getInt("matricula"));
                aluno.setNome(rs.getString("nome"));
                aluno.setNascimento(rs.getDate("nascimento"));
                aluno.setEndereco(rs.getString("endereco"));
                listaDeAluno.add(aluno);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return listaDeAluno;
    }

    public Aluno getAlunoById(int matricula) {
        Aluno aluno = new Aluno();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM alunos WHERE matricula=?");
            preparedStatement.setInt(1, matricula);
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                aluno.setMatricula(rs.getInt("matricula"));
                aluno.setNome(rs.getString("nome"));
                aluno.setNascimento(rs.getDate("nascimento"));
                aluno.setEndereco(rs.getString("endereco"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return aluno;
    }
}