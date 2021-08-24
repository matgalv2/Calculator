package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

import static sample.ButtonType.*;
import static sample.Operator.*;


public class Controller
{

    @FXML
    private Label result;

    private String [] stored_numbers = {"0", ""};
    private final int first_index = 0;
    private final int second_index = 1;
    private int current_index = 0;
    private Operator operator;
    private ButtonType last_button;

    public Controller()
    {
        last_button = OPERATOR;
    }


    private void deleteLeadingZero()
    {
        if(stored_numbers[current_index].equals("0"))
            stored_numbers[current_index] = "";
        else if(stored_numbers[current_index].equals("-0"))
            stored_numbers[current_index] = "-";
    }


    private void deleteTrailingZero(int index)
    {
        /*Non-necessary zero can appear only in situations like x.0*/
        String number = stored_numbers[index];
        if(number.substring(number.length()-2).equals(".0"))
            stored_numbers[index] = number.substring(0,number.length()-2);
    }

    private void check(Operator operation)
    {
        if (operator == null)
        {
            current_index = second_index;
        }
        else if (!stored_numbers[first_index].equals("") && !stored_numbers[second_index].equals("") && last_button != EQUAL && operator == operation)
            count();
        else
            current_index = second_index;

        stored_numbers[second_index] = "";

    }

    private void count()
    {
        if(operator == null || stored_numbers[second_index].equals("")|| stored_numbers[second_index].equals("-"))
            return;

        else if (operator == ADD)
            stored_numbers[first_index] = String.valueOf(Double.parseDouble(stored_numbers[first_index]) + Double.parseDouble(stored_numbers[second_index]));

        else if (operator == SUBTRACT)
            stored_numbers[first_index] = String.valueOf(Double.parseDouble(stored_numbers[first_index]) - Double.parseDouble(stored_numbers[second_index]));

        else if (operator == MULTIPLY)
            stored_numbers[first_index] = String.valueOf(Double.parseDouble(stored_numbers[first_index]) * Double.parseDouble(stored_numbers[second_index]));

        else if (operator == DIVIDE)
        {
            if (!stored_numbers[second_index].equals("0"))
                stored_numbers[first_index] = String.valueOf(Double.parseDouble(stored_numbers[first_index]) / Double.parseDouble(stored_numbers[second_index]));
            else
                {
                    changeScreen("ERROR");
                    stored_numbers = new String[]{"0", ""};
                    current_index = first_index;
                    operator = null;
                    last_button = OPERATOR;
                    return;
                }
        }

        else if (operator == POWER)
            stored_numbers[first_index] = String.valueOf(Math.pow(Double.parseDouble(stored_numbers[first_index]), Double.parseDouble(stored_numbers[second_index])));

        else if (operator == PERCENT)
            stored_numbers[first_index] = String.valueOf(Double.parseDouble(stored_numbers[first_index]) * 0.01 * Double.parseDouble(stored_numbers[second_index]));

        else if (operator == ROOT)
            root();

        deleteTrailingZero(first_index);
        changeScreen(stored_numbers[first_index]);
        current_index = second_index;

    }

    private void appendNumber(int next_number)
    {
        stored_numbers[current_index] += next_number;
    }

    private void changeScreen(String number)
    {
        if(number.length()>10)
            result.setText(number.substring(0,11));
        else
            result.setText(number);
    }

    /*Buttons commands*/
    public void zero()
    {
        deleteLeadingZero();
        appendNumber(0);
        changeScreen(stored_numbers[current_index]);
        last_button = NUMBER;
    }

    public void one()
    {
        deleteLeadingZero();
        appendNumber(1);
        changeScreen(stored_numbers[current_index]);
        last_button = NUMBER;
    }

    public void two()
    {
        deleteLeadingZero();
        appendNumber(2);
        changeScreen(stored_numbers[current_index]);
        last_button = NUMBER;

    }

    public void three()
    {
        deleteLeadingZero();
        appendNumber(3);
        changeScreen(stored_numbers[current_index]);
        last_button = NUMBER;

    }

    public void four()
    {
        deleteLeadingZero();
        appendNumber(4);
        changeScreen(stored_numbers[current_index]);
        last_button = NUMBER;

    }

    public void five()
    {
        deleteLeadingZero();
        appendNumber(5);
        changeScreen(stored_numbers[current_index]);
        last_button = NUMBER;

    }

    public void six()
    {
        deleteLeadingZero();
        appendNumber(6);
        changeScreen(stored_numbers[current_index]);
        last_button = NUMBER;

    }

    public void seven()
    {
        deleteLeadingZero();
        appendNumber(7);
        changeScreen(stored_numbers[current_index]);
        last_button = NUMBER;

    }

    public void eight()
    {
        deleteLeadingZero();
        appendNumber(8);
        changeScreen(stored_numbers[current_index]);
        last_button = NUMBER;

    }

    public void nine()
    {
        deleteLeadingZero();
        appendNumber(9);
        changeScreen(stored_numbers[current_index]);
        last_button = NUMBER;

    }

    public void add()
    {
        check(ADD);
        operator = ADD;
        last_button = OPERATOR;
    }

    public void subtract()
    {
        if(stored_numbers[current_index].equals("0"))
            stored_numbers[current_index] = "-0";
        else{
                check(SUBTRACT);
                operator = SUBTRACT;
            }
        last_button = OPERATOR;
    }

    public void multiply()
    {
        check(MULTIPLY);
        operator = MULTIPLY;
        last_button = OPERATOR;
    }

    public void divide()
    {
        check(DIVIDE);
        operator = DIVIDE;
        last_button = OPERATOR;
    }

    public void root()
    {
        if (last_button == EQUAL || operator == ROOT || stored_numbers[second_index].equals(""))
        {
            current_index = first_index;
            stored_numbers[second_index] = "";

        }
        else
            current_index = second_index;


        last_button = OPERATOR;

        if(!stored_numbers[current_index].equals("") && Double.parseDouble(stored_numbers[current_index]) >= 0.0)
        {
            double d = Math.sqrt(Double.parseDouble(stored_numbers[current_index]));
            int i = (int)d;

            if (d == i)
                stored_numbers[current_index] = String.valueOf(i);
            else
                stored_numbers[current_index] = String.valueOf(d);

            changeScreen(stored_numbers[current_index]);
        }
        else
        {
            changeScreen("ERROR");
            stored_numbers = new String[]{"0", ""};
            current_index = first_index;
            operator = null;
            last_button = OPERATOR;

        }
        operator = ROOT;
    }

    public void power()
    {
        check(POWER);
        operator = POWER;
        last_button = OPERATOR;
    }

    public void percent()
    {
        check(PERCENT);
        operator = PERCENT;
        last_button = OPERATOR;
    }

    public void equals()
    {
        count();
        last_button = EQUAL;
    }

    public void clear()
    {
        stored_numbers = new String[] {"0", ""};
        current_index = 0;
        changeScreen(stored_numbers[current_index]);
    }

    public void point()
    {
        stored_numbers[current_index] += '.';
    }

}