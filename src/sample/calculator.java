package sample;
import java.util.ArrayList;
import java.util.Stack;

public class calculator {
    private double number = 0;
    private Stack<Double> numberArray = new Stack<>();
    private Stack <Character> operation = new Stack<>();
    public double getNumber(){
        return this.number;
    }
    public static void  main(String args[]){
        System.out.println(new calculator().cal("9+(3-1)*3+10/2"));
    }
    //将中缀表达式转换为后缀表达式
    private  String transferToPostfix(String expression){
        String postfixExpression="";
        char[] expressions=expression.toCharArray();
        for(int i=0;i<expressions.length;i++){



            /**
             * 当遇到数字的时候
             * 将数字输出
             */


            if(expressions[i]>='0'&&expressions[i]<='9') {
                postfixExpression += expressions[i];
            }


            /**
             * 当遇到加号或者减号的时候
             */


            if( expressions[i] == '-'
                    ||expressions[i] == '+'
                    ){
                //当空栈，则将符号压入栈中
                if(operation.isEmpty()){
                    operation.push( expressions[i] );
                }else{
                    /*
                    当栈非空的时候，判断栈顶元素，
                    若栈顶元素为左括号，则将符号压栈
                    若栈顶元素为加减或乘除，则将栈顶元素弹出
                     */
                    while(!operation.isEmpty()) {
                        if(operation.peek()!='(') {
                            postfixExpression += operation.pop();
                        } else {
                            operation.push(expressions[i]);
                            break;
                        }
                    }
                    if(operation.isEmpty())
                        operation.push(expressions[i]);
                }
            }



            /**
             *当遇到左括号的时候
             */



            if(expressions[i] == '('){
                operation.push(expressions[i]);
            }



            /**
             * 当遇到右括号的时候
             */


            if(expressions[i] == ')'){
                    while (operation.peek() != '(')
                    {
                        postfixExpression += operation.pop();
                    }
                    operation.pop();
            }



            /**
             * 当遇到乘除号的时候
             */



            if (expressions[i] == '*'
                    || expressions[i] =='/'){
                while(!operation.isEmpty()){
                    if(operation.peek() == '+'
                            ||operation.peek() == '-'){
                        operation.push(expressions[i]);
                        break;
                    }
                    if(operation.peek() == '*'
                            ||operation.peek() == '/'){
                        postfixExpression += operation.pop();
                    }
                    if(operation.peek() == '('){
                        operation.push(expressions[i]);
                        break;
                    }
                }
                if(operation.isEmpty())
                    operation.push(expressions[i]);
            }
        }



        /**
         * 最后将栈中所有元素弹出
         */



        while (!operation.isEmpty()){
            postfixExpression += operation.pop();
        }
        System.out.println(postfixExpression);
        return postfixExpression;

    }
//    计算后缀表达式

    /**
     *
     * @param postfixExpression
     * @return
     */
    private double calculatePostfix(String postfixExpression){
        char [] postfixExpressions = postfixExpression.toCharArray();
        double left=0;
        double right=0;
        double temp=0;
        double result=0;
        for (int i=0;i<postfixExpressions.length;i++){
            if (postfixExpressions[i]>='0'&&postfixExpressions[i]<='9'){
                numberArray.push((double)(postfixExpressions[i]-'0'));
            }else{
                right=numberArray.pop();
                left=numberArray.pop();
                if(postfixExpressions[i] == '+'){
                    temp = left + right;
                }else if(postfixExpressions[i] == '-'){
                    temp = left - right;
                }else if(postfixExpressions[i] == '*'){
                    temp = left * right;
                }else if(postfixExpressions[i] == '/'){
                    temp = left / right;
                }else if(postfixExpressions[i] == '%'){
                    temp = left % right;
                }
                numberArray.push(temp);
            }
        }
        result = numberArray.pop();
        return result;
    }

    /**
     *
     * @param expression
     * @return
     */
    public double cal(String expression){
        String postfixExpression="";
        postfixExpression=transferToPostfix(expression);
        double result=0;
        result=calculatePostfix(postfixExpression);
        return result;
    }
}
