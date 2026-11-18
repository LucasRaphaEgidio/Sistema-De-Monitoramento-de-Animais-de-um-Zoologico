package br.edu.utfpr.zoologico;

import br.edu.utfpr.zoologico.exception.PersistenciaException;
import br.edu.utfpr.zoologico.infra.DatabaseInitializer;
import br.edu.utfpr.zoologico.view.TelaPrincipal;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                DatabaseInitializer.criarEstrutura();
                new TelaPrincipal().setVisible(true);
            } catch (PersistenciaException exception) {
                JOptionPane.showMessageDialog(null,
                        exception.getMessage() + "\n\nConfira se o MySQL esta em execucao, se o banco foi criado"
                                + " (database/schema.sql) e se ZOO_DB_PASSWORD (e, se necessario, ZOO_DB_USER)"
                                + " foi definida no terminal.",
                        "Falha na conexao com o banco", JOptionPane.ERROR_MESSAGE);
            } catch (Exception exception) {
                JOptionPane.showMessageDialog(null, exception.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });
    }
}
