package darm.calculadora3.com;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Integer digit = 0;
    TextView display;
    String cad = "";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        display = findViewById(R.id.display);
    }

    public void digit(android.view.View vista)
    {
        Button boton = (Button) vista;
        //Toast.makeText(this, "Boton apretado: " + boton.getText(), Toast.LENGTH_SHORT).show();
        cad = String.valueOf(display.getText());
        if(cad.equals("0"))
            display.setText("");
        if((cad.charAt(cad.length()-1)=='+' || cad.charAt(cad.length()-1)=='-' || cad.charAt(cad.length()-1)=='x' || cad.charAt(cad.length()-1)=='/') && (boton.getText().equals("+") || boton.getText().equals("-") || boton.getText().equals("x") || boton.getText().equals("/")))
        {
            cad = cad.substring(0, cad.length()-1);
            display.setText(cad);
        }
        cad = String.valueOf(display.getText());
        if(cad.equals("") && (boton.getText().equals("+") || boton.getText().equals("x") || boton.getText().equals("/")))
        {
            display.setText("0");
        }
        else
            display.append(boton.getText());
    }

    public void borrar(android.view.View vista)
    {
        cad = String.valueOf(display.getText());
        cad = cad.substring(0, cad.length()-1);
        if(cad.equals("") || cad.equals("-"))
            display.setText("0");
        else
            display.setText(cad);
    }

    public void resultado(android.view.View vista)
    {
        String n1="",n2="",n1_aux="";
        char[] tempArrayChar;
        double res = 0;
        int i = 0;
        boolean por = false;
        cad = String.valueOf(display.getText());
        while(cad.indexOf("x")!=-1)
        {
            res = 0;
            n1 = "";
            n1_aux = "";
            n2 = "";
            for(i=cad.indexOf("x")-1;i>=0 && cad.charAt(i)!='+' && cad.charAt(i)!='-' && cad.charAt(i)!='/' && cad.charAt(i)!='x';i--)
            {
                n1 += Character.toString(cad.charAt(i));
                tempArrayChar = cad.toCharArray();
                tempArrayChar[i] = 'd';
                cad = String.valueOf(tempArrayChar);
            }
            for(i=n1.length()-1;i>=0;i--)
            {
                n1_aux += Character.toString(n1.charAt(i));
            }
            n1=n1_aux;
            for(i=cad.indexOf("x")+1;i<cad.length() && cad.charAt(i)!='+' && cad.charAt(i)!='-' && cad.charAt(i)!='/' && cad.charAt(i)!='x';i++)
            {
                n2 += Character.toString(cad.charAt(i));
                tempArrayChar = cad.toCharArray();
                tempArrayChar[i] = 'd';
                cad = String.valueOf(tempArrayChar);
            }
            cad = cad.substring(0,cad.indexOf("x")) + cad.substring(cad.indexOf("x")+1,cad.length());
            res = Double.parseDouble(n1) * Double.parseDouble(n2);
            for(i=cad.indexOf("d");i<cad.length();i++)
                if(cad.charAt(i)!='d')
                    break;
            cad = cad.substring(0,cad.indexOf("d")) + String.valueOf(res) + cad.substring(i,cad.length());
        }
        while(cad.indexOf("/")!=-1)
        {
            res = 0;
            n1 = "";
            n1_aux = "";
            n2 = "";
            for(i=cad.indexOf("/")-1;i>=0 && cad.charAt(i)!='+' && cad.charAt(i)!='-' && cad.charAt(i)!='/' && cad.charAt(i)!='x';i--)
            {
                n1 += Character.toString(cad.charAt(i));
                tempArrayChar = cad.toCharArray();
                tempArrayChar[i] = 'd';
                cad = String.valueOf(tempArrayChar);
            }
            for(i=n1.length()-1;i>=0;i--)
            {
                n1_aux += Character.toString(n1.charAt(i));
            }
            n1=n1_aux;
            for(i=cad.indexOf("/")+1;i<cad.length() && cad.charAt(i)!='+' && cad.charAt(i)!='-' && cad.charAt(i)!='/' && cad.charAt(i)!='x';i++)
            {
                n2 += Character.toString(cad.charAt(i));
                tempArrayChar = cad.toCharArray();
                tempArrayChar[i] = 'd';
                cad = String.valueOf(tempArrayChar);
            }
            cad = cad.substring(0,cad.indexOf("/")) + cad.substring(cad.indexOf("/")+1,cad.length());
            res = Double.parseDouble(n1) / Double.parseDouble(n2);
            for(i=cad.indexOf("d");i<cad.length();i++)
                if(cad.charAt(i)!='d')
                    break;
            cad = cad.substring(0,cad.indexOf("d")) + String.valueOf(res) + cad.substring(i,cad.length());
        }
        while(cad.contains("+"))
        {
            res = 0;
            n1 = "";
            n1_aux = "";
            n2 = "";
            boolean neg = false;
            for(i=cad.indexOf("+")-1;i>=0 && cad.charAt(i)!='+';i--)
            {
                if(cad.charAt(i)=='-')
                    neg = true;
                n1 += Character.toString(cad.charAt(i));
                tempArrayChar = cad.toCharArray();
                tempArrayChar[i] = 'd';
                cad = String.valueOf(tempArrayChar);
                if(neg)
                    break;
            }
            for(i=n1.length()-1;i>=0;i--)
            {
                n1_aux += Character.toString(n1.charAt(i));
            }
            n1=n1_aux;
            for(i=cad.indexOf("+")+1;i<cad.length() && cad.charAt(i)!='+' && cad.charAt(i)!='-';i++)
            {
                n2 += Character.toString(cad.charAt(i));
                tempArrayChar = cad.toCharArray();
                tempArrayChar[i] = 'd';
                cad = String.valueOf(tempArrayChar);
            }
            res = Double.parseDouble(n1) + Double.parseDouble(n2);
            cad = cad.substring(0, cad.indexOf('d')) + String.valueOf(res) + cad.substring(cad.lastIndexOf('d')+1, cad.length());
        }
        while(cad.contains("-"))
        {
            res = 0;
            n1 = "";
            n1_aux = "";
            n2 = "";
            if(cad.indexOf("-")==0)//Negativo en el primer elemento
            {
                if(cad.length()==1)
                    break;
                i=0;
                do
                {
                    n1 += Character.toString(cad.charAt(i));
                    tempArrayChar = cad.toCharArray();
                    tempArrayChar[i] = 'd';
                    cad = String.valueOf(tempArrayChar);
                    i++;
                }
                while(i<cad.length() && cad.charAt(i)!='-');
                if(i==cad.length())//Si ya llego al final, solo hay un numero negativo
                {
                    res = Double.parseDouble(n1);
                    cad = String.valueOf(res);
                    break;
                }
                else//Hay mas numeros negativos
                {
                    i=cad.indexOf("-");
                    do
                    {
                        n2 += Character.toString(cad.charAt(i));
                        tempArrayChar = cad.toCharArray();
                        tempArrayChar[i] = 'd';
                        cad = String.valueOf(tempArrayChar);
                        i++;
                    }
                    while(i<cad.length() && cad.charAt(i)!='-');
                }
                cad = cad.substring(cad.lastIndexOf('d')+1,cad.length());
                res = Double.parseDouble(n1) + Double.parseDouble(n2);
                cad = String.valueOf(res) + cad;
            }
            else//El primero elemento no es negativo
            {
                for(i=cad.indexOf("-")-1;i>=0;i--)
                {
                    n1 += Character.toString(cad.charAt(i));
                    tempArrayChar = cad.toCharArray();
                    tempArrayChar[i] = 'd';
                    cad = String.valueOf(tempArrayChar);
                }
                for(i=n1.length()-1;i>=0;i--)
                {
                    n1_aux += Character.toString(n1.charAt(i));
                }
                n1=n1_aux;
                i=cad.indexOf("-");
                do
                {
                    n2 += Character.toString(cad.charAt(i));
                    tempArrayChar = cad.toCharArray();
                    tempArrayChar[i] = 'd';
                    cad = String.valueOf(tempArrayChar);
                    i++;
                }
                while(i<cad.length() && cad.charAt(i)!='-');
                cad = cad.substring(cad.lastIndexOf('d')+1,cad.length());
                res = Double.parseDouble(n1) + Double.parseDouble(n2);
                cad = String.valueOf(res) + cad;
            }
        }
        for(i=cad.indexOf(".")+1;i<cad.length();i++)
        {
            if(cad.charAt(i)!='0')
                por = true;
        }
        if(!por)
            cad = cad.substring(0, cad.indexOf("."));
        display.setText(cad);
    }
}
