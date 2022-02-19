/*import java.io.*;
import java.io.InputStreamReader;
import java.io.IOException;
import java.security.KeyStore;
import java.util.ArrayList;
import java.util.Scanner;
import java.lang.String;

public class checkTheCheck {

    static void print(String[][] chessboard){
        for (int a=0; a<8; a++){
            for( int b=0; b<8; b++){
                System.out.println(a);
            }
            System.out.println(a);
        }
    }

    public static void main(String[] args) throws IOException{
        /*BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();
        System.out.println(input);*/
/*        Scanner reader= new Scanner(System.in);

        }

    }

}

        bool boardflag=false;
        for(int row=0; row<8; row++)
        {
        for( int col=0; col<8; col++)
        {
        cin>>chessboard[row][col];
        cout<<chessboard[row][col];
        if(chessboard[row][col]!=".")
        {
        boardflag = true;
        }
        }
        }
        print(chessboard);
        if(boardflag==false){
        mainflag=false;

        #include <iostream>
#include <ctype.h>
        using namespace std;

        void print(char chessboard[8][8])
        {
        for (int a=0; a<8; a++)
        {
        for( int b=0; b<8; b++)
        {
        cout<<chessboard[a][b];
        }
        cout<<"\n";
        }
        }

//void pawnAttack(char chessboard[8][8]);      g
//void knightAttack(char chessboard[8][8]);    g
//void bishopAttack(char chessboard[8][8]);    g
//void rookAttack(char chessboard[8][8]);      g
//void kingAttack(char chessboard[8][8]); //pawnwhite

        void check(char chessboard[8][8], int whiteKRow, int whiteKCol, int blackKRow, int blackKCol)
        {
        if(!checkforalldots(chessboard))
        {
        if(checkForWhiteKing(chessboard), whiteKRow, whiteKCol)
        {
        cout<<"Game #"<<++board<<": white king is in check.";
        }
        else if(checkForBlackKing(chessboard), blackKRow, blackKCol)
        {
        cout<<"Game #"<<++board<<": black king is in check.";
        }
        else
        {
        cout<<"Game #"<<++board<<": no king is in check.";
        }
        }
        }

        bool checkForWhiteKing(char chessboard[8][8], int whiteKRow, int whiteKCol)
        {
        int row,col;
        char king="K";
        bool attacked=false;
        for(row=0; row<8; row++)
        {
        for(col=0;col<8;col++)
        {
        if(chessboard[row][col]!='.' && islower(chessboard[row][col]))
        {
        if(chessboard[row][col]=='p')
        {
        if(kingAttack(row, col, whiteKRow, whiteKCol, king))
        {
        attacked=true;
        }
        }
        else if(chessboard[row][col]=='n')
        {
        if(knightAttack(row, col, whiteKRow, whiteKCol, king))
        {
        attacked=true;
        }
        }
        else if(chessboard[row][col]=='b')
        {
        if(bishopAttack(row, col, whiteKRow, whiteKCol, king))
        {
        attacked=true;
        }
        }
        else if(chessboard[row][col]=='r')
        {
        if(rookAttack(row, col, whiteKRow, whiteKCol, king))
        {
        attacked=true;
        }
        }
        else if(chessboard[row][col]=='q')
        {
        if(rookAttack(row, col, whiteKRow, whiteKCol, king) || bishopAttack(row, col, whiteKRow, whiteKCol, king))
        {
        attacked=true;
        }
        }
        }
        }
        }
        return attacked;
        }
        bool checkForBlackKing(char chessboard[8][8], int blackKRow, int blackKCol)
        {
        //cout<<"Checking for black";
        //cout<<"\n";
        return false;
        }

        bool checkforalldots(char chessboard[8][8])
        {
        int row,col;
        bool flag=true;
        char piece;
        for(row=0;row<8;row++)
        {
        for(col=0;col<8;col++)
        {
        piece=chessboard[row][col];
        if(piece!='.')
        {
        flag=false;
        }
        }
        }
        return flag;
        }

        int main()
        {
        bool mainflag= true;
        char piece;
        int row,col, whiteKRow, whiteKCol, blackKRow, blackKCol;
        char chessboard[8][8];
        int board=0;
        while(mainflag)
        {
        bool boardflag=false;
        for(row=0; row<8; row++)
        {
        for(col=0; col<8; col++)
        {
        cin>>chessboard[row][col];
        piece=chessboard[row][col];
        if(piece!='.')
        {
        boardflag=true;
        }
        if(piece=='K')
        {
        whiteKRow=row;
        whiteKCol=col;
        }
        if(piece=='k')
        {
        blackKRow=row;
        blackKCol=col;
        }
        }
        }
        //print(chessboard);
        check(chessboard, whiteKRow, whiteKCol, blackKRow, blackKCol);
        cout<<"\n";
        if(boardflag==false)
        {
        mainflag=false;
        }
        }
        }
*/