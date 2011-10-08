package transitorj.view;

import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import transitorj.controller.ControleBusca;
import transitorj.model.Crawler;

public class TelaBusca extends JFrame implements ActionListener {
        
		private Crawler crawler = new Crawler();
        private static final long serialVersionUID = 1L;
        
        JLabel labelRua,labelErro;
        JTextField textRua;
        JButton botaoPesquisar;
        JList listaTweets;
        //JTextArea areaTweets;
        JScrollPane scrollTweets;
        
        ControleBusca controleBusca;
        
        public TelaBusca() {
                setTitle("Tr창nsito RJ App");
                
                controleBusca = new ControleBusca();
                
                inicializaComponentes();
                carregaUI();
                pack();
                setVisible(true);
                setDefaultCloseOperation(EXIT_ON_CLOSE);
                
                setSize(800, 600);
                
                crawler.run();
        }
        
        public void inicializaComponentes() {
                // Labels
                labelRua = new JLabel("Rua:");
                labelErro = new JLabel("N찾o insira o tipo de logradouro no campo de busca." + '\n' + "Ex: Para buscar Avenida Brasil, digite apenas Brasil");
                
                // TextField
                textRua = new JTextField(30);
                
                // Bot천es
                botaoPesquisar = new JButton("Pesquisar");
                botaoPesquisar.addActionListener(this);
                
                // Tweets
                listaTweets = new JList();
                scrollTweets = new JScrollPane(listaTweets);
                
                // 햞ea Tweets
                //areaTweets = new JTextArea(20, 40);
                //scrollTweets = new JScrollPane(areaTweets);
        }
        
        public void carregaUI() {
                Container conteudo = getContentPane();
                GridBagLayout layout = new GridBagLayout();
                GridBagConstraints constraint = new GridBagConstraints();
                conteudo.setLayout(layout);             
                
                constraint.fill = GridBagConstraints.HORIZONTAL;
                
                constraint.gridx = 0;
                constraint.gridy = 0;
                conteudo.add(labelRua, constraint);
                
                constraint.gridx = 1;
                constraint.gridy = 0;   
                conteudo.add(textRua, constraint);
                
                constraint.gridx = 1;
                constraint.gridy = 2;
                conteudo.add(labelErro, constraint);
                
                constraint.gridx = 1;
                constraint.gridy = 3;
                //constraint.anchor = GridBagConstraints.PAGE_END;              
                constraint.insets = new Insets(20, 60, 50, 50);
                conteudo.add(botaoPesquisar, constraint);
                
                constraint.gridx = 0;
                constraint.gridy = 5;
                constraint.gridwidth = 2;
                //conteudo.add(listaTweets, constraint);
                conteudo.add(scrollTweets, constraint);
        }

        @Override
        public void actionPerformed(ActionEvent arg0) {
                if(arg0.getSource() == botaoPesquisar) {                        
                    listaTweets.setListData(controleBusca.buscaEntrada(textRua.getText()));                 
                }
        }

        /**
         * @param args
         */
        public static void main(String[] args) {
                new TelaBusca();
        }

}