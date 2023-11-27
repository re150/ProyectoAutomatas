package automata2;

import automata1.Automatas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import javax.swing.filechooser.FileNameExtensionFilter;


public class Editor extends JFrame  {
    int i = 0;
    private JPanel panelPrincipal = new JPanel();
    private JPanel panelSuperior = new JPanel();
    private JPanel panelInferior = new JPanel();
    private JPanel panelBotones = new JPanel();
    private JTextArea editor1 = new JTextArea();
    private JTextArea editor2 = new JTextArea();
    private JButton btnSave = new JButton("Save");
    private JButton btnStart = new JButton("Start");
    private JButton btnSelect = new JButton("Select");

    public FileReader archivo;
    public  BufferedReader lector;
    JFileChooser fileChooser = new JFileChooser();

    public Editor() {
        setSize(800, 600);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
        init();
        editor2.setEditable(false);

        FileNameExtensionFilter filter = new FileNameExtensionFilter("Archivos de texto", "txt");
        fileChooser.setFileFilter(filter);

        btnSelect.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                readFile();
            }
        });
        btnSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String contenido = editor1.getText();
                if(!contenido.isEmpty()) {
                    System.out.println(contenido.isEmpty());
                    int resultado = fileChooser.showSaveDialog(null);
                    if (resultado == JFileChooser.APPROVE_OPTION) {
                    java.io.File archivoSeleccionado = fileChooser.getSelectedFile();
                    try (FileWriter escritor = new FileWriter(archivoSeleccionado.getAbsolutePath())) {
                        escritor.write(editor1.getText());
                       // System.out.println("Cambios guardados en: " + archivoSeleccionado.getAbsolutePath());
                    } catch (IOException ex) {
                    }
                }
              } else {
                    JOptionPane.showMessageDialog(null, "No se seleccionó un  archivo");
                }
            }
        });
        btnStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    editor2.setText("");
                    String contenido ="";
                    contenido= getarchivoTextarea() + "\n";

                    Automata a  = new Automata(contenido);
                    editor2.setText(a.toString());

                }catch (Exception ex){

                }
            }
        });
    }

    public void init() {
        //  principal
        panelPrincipal.setLayout(new GridLayout(2, 1));
        panelPrincipal.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panelSuperior.setLayout(new BorderLayout());
        panelSuperior.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        //inferio
        panelInferior.setLayout(new BorderLayout());
        panelInferior.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        // Agregando padding

        // scroll
        JScrollPane scrollPane1 = new JScrollPane(editor1);
        panelSuperior.add("Center", scrollPane1);

        // scroll inferio
        JScrollPane scrollPane2 = new JScrollPane(editor2);
        panelInferior.add("Center", scrollPane2);

        // size de los bootnes
        btnSelect.setMaximumSize(new Dimension(90,20));
        btnSave.setMaximumSize(new Dimension(90,20));
        btnStart.setMaximumSize(new Dimension(90,20));
        // espacion entre los botones
        panelBotones.setLayout(new BoxLayout(panelBotones, BoxLayout.Y_AXIS));
        panelBotones.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panelBotones.add(Box.createVerticalStrut(30));
        panelBotones.add(btnSelect);
        panelBotones.add(Box.createVerticalStrut(30));
        panelBotones.add(btnSave);
        panelBotones.add(Box.createVerticalStrut(30));
        panelBotones.add(btnStart);
        panelSuperior.add("East", panelBotones);


        panelPrincipal.add(panelSuperior);
        panelPrincipal.add(panelInferior);
        getContentPane().add(panelPrincipal);

    }

    public void readFile() {
        int resultado = fileChooser.showOpenDialog(null);
        if (resultado == JFileChooser.APPROVE_OPTION) {
            java.io.File archivoSeleccionado = fileChooser.getSelectedFile();
            try {
                archivo = new FileReader(archivoSeleccionado.getAbsolutePath());
                editor1.read(archivo, null);
            //    System.out.println("Archivo seleccionado: " + archivo);

            } catch (FileNotFoundException ex) {
            } catch (Exception ex) {
            } finally {
                try {
                    archivo.close();
                } catch (IOException ex) {
                    System.err.print(ex);
                }
            }
        } else {
            System.out.println("Selección de archivo cancelada por el usuario");
        }
    }

    public String getarchivoTextarea(){
        String contenido = editor1.getText();
        if(contenido.isEmpty()){
            JOptionPane.showMessageDialog(null,"No se seleccionó un  archivo");
        }
        return contenido;
    }

}
