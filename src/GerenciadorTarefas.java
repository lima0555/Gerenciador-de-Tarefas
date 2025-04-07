
import java.awt.Color;
import java.awt.Font;
import java.io.FileWriter;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.text.*;





public class GerenciadorTarefas {


	public static void main(String[] args) {

		JFrame janela = new JFrame("Ajuda Rapida");
		janela.setSize(500, 590);
		janela.setLayout(null);
		janela.setResizable(false);
		janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		janela.getContentPane().setBackground(Color.decode("#36344F"));


		Font fonte = new Font("Arial", Font.PLAIN, 16);
		Font fontetit = new Font("Arial", Font.BOLD, 27);
		Color cortext = Color.white;

		JLabel nome = new JLabel("F . A . S . T");
		nome.setFont(fontetit);
		nome.setForeground(Color.white);
		nome.setBounds(10, 30, 200, 20);
		janela.add(nome);


		/* Nome da empresa */
		JLabel nomeemp = new JLabel("Nome da empresa");
		nomeemp.setBounds(10, 90, 200, 20);
		nomeemp.setFont(fonte);
		nomeemp.setForeground(cortext);
		janela.add(nomeemp);

		JTextField text1 = new JTextField();
		text1.setBounds(10, 110, 290, 30);
		text1.setForeground(Color.BLACK);
		text1.setBackground(Color.white);
		text1.setFont(fonte);
		janela.add(text1);



		/* Cpf / Cnpj */
		JLabel cpnj = new JLabel("CPF / CNPJ");
		cpnj.setBounds(10, 180, 200, 20);
		cpnj.setFont(fonte);
		cpnj.setForeground(cortext);
		janela.add(cpnj);

		JTextField text2 = new JTextField();
		text2.setBounds(10, 200, 290, 30);
		text2.setBackground(Color.white);
		text2.setForeground(Color.black);
		text2.setFont(fonte);

		text2.setDocument(new PlainDocument(){
			@Override

			public void insertString(int offs, String str, AttributeSet a) throws BadLocationException{

				if(str == null || getLength() >= 14) return;
				super.insertString(offs, str, a);
			}
		});

		janela.add(text2);

		JLabel problemas = new JLabel("Problemas com o produto");
		problemas.setFont(fonte);
		problemas.setBounds(10, 275, 200, 20);
		problemas.setForeground(cortext);
		janela.add(problemas);

		String [] text3 = {" ","Queda", "erro na voltagem", "falta de peças", "produto danificado", "não liga", "bateria fraca","Outro"};
		JComboBox<String> probleBox = new JComboBox<>(text3);
		probleBox.setBounds(10, 295, 290, 30);
		probleBox.setForeground(Color.black);
		probleBox.setBackground(Color.white);
		probleBox.setFont(fonte);
		janela.add(probleBox);


		/* Caso a opçaõ outro seja selecionada*/
		JTextArea outro = new JTextArea();
		outro.setBounds(10, 370, 290, 80);
		outro.setLineWrap(true);
		outro.setWrapStyleWord(true);
		outro.setFont(fonte);
		outro.setVisible(false);
		janela.add(outro);

		JScrollPane scroll = new JScrollPane(outro);
		scroll.setBounds(10, 370, 290, 80);
		scroll.setVisible(false);
		janela.add(scroll);

		JButton enviar = new JButton("ENVIAR");
		enviar.setBackground(Color.white);
		enviar.setFont(fonte);
		enviar.setBounds(330, 510, 140, 30);
		janela.add(enviar);


		probleBox.addActionListener(e -> {
			if(probleBox.getSelectedItem().equals("Outro")){
				JLabel prob2 = new JLabel("Explique o problema");
				prob2.setBounds(10, 340, 200, 20);
				prob2.setForeground(cortext);
				prob2.setFont(fonte);
				janela.add(prob2);
				outro.setVisible(true);
				scroll.setVisible(true);

			} else {
				outro.setVisible(false);
			}
		});

		enviar.addActionListener(e ->{
			try(FileWriter dados = new FileWriter("C:\\Users\\limac\\OneDrive\\Área de Trabalho\\c e java\\DefeitosProdutos.txt", true)){

			String empresa = text1.getText();
			String cpfcnpj = text2.getText();
			String outropt2 = outro.getText();
			String proble = (probleBox.getSelectedItem() != null) ? probleBox.getSelectedItem().toString(): "";

			String todosprod = "Nome da empresa: -> " + empresa + "\n" +
								"Cpf/Cnpj: -> " + cpfcnpj + "\n" + 
								"Problema com o produto: -> " + proble + "\n";
								

			if("Outro".equals(proble)){
				todosprod += "Descrição do problema: -> " + outropt2 + "\n";
			}

			todosprod += "-------------------------\n";

			dados.write(todosprod);
			JOptionPane.showMessageDialog(null, "Solicitação enviada com sucesso!");

			text1.setText("");
			text2.setText("");
			outro.setText("");
			probleBox.setSelectedItem(-1);


			}catch(Exception ex){
				ex.printStackTrace();
				JOptionPane.showMessageDialog(null, "ERRO");
			}
		});

		


		

		janela.setVisible(true);
        
    }
}
		
		
