import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

default class GerenciadorTarefas {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Gerenciador de Tarefas");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        DefaultListModel<String> modeloLista = new DefaultListModel<>();
        JList<String> listaTarefas = new JList<>(modeloLista);
        JTextField campoTexto = new JTextField();
        JButton botaoAdicionar = new JButton("Adicionar");
        JButton botaoRemover = new JButton("Remover");

        JPanel painelEntrada = new JPanel();
        painelEntrada.setLayout(new BorderLayout());
        painelEntrada.add(campoTexto, BorderLayout.CENTER);
        painelEntrada.add(botaoAdicionar, BorderLayout.EAST);

        frame.add(painelEntrada, BorderLayout.NORTH);
        frame.add(new JScrollPane(listaTarefas), BorderLayout.CENTER);
        frame.add(botaoRemover, BorderLayout.SOUTH);

        botaoAdicionar.addActionListener(e -> {
            String tarefa = campoTexto.getText().trim();
            if (!tarefa.isEmpty()) {
                modeloLista.addElement(tarefa);
                campoTexto.setText("");
            }
        });

        botaoRemover.addActionListener(e -> {
            int index = listaTarefas.getSelectedIndex();
            if (index != -1) {
                modeloLista.remove(index);
            }
        });

        frame.setVisible(true);
    }
}
