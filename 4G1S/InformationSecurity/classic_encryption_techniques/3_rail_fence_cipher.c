#include <stdio.h>

int rail;

int main()
{
    printf("Your rail : ");
    scanf("%d", &rail);

    int row_num = 0, col_num = 0, size = 0, select = 0, cnt = 0;
    char text[64], tmp_text[rail][64 / rail], c_text[64], d_text[64];

    printf("Your plain text : ");
    scanf("%s", text);
    size = strlen(text);

    for (col_num = 0; col_num < size; col_num++)
    {
        if (((text[col_num] >= 'a') && (text[col_num] <= 'z')) || ((text[col_num] >= 'A') && (text[col_num] <= 'Z')))
        {
            tmp_text[col_num % rail][col_num / rail] = text[col_num];
        }
        else
            ;
    }
    for (col_num = 0; col_num < (size / rail); col_num++)
    {
        for (int i = 0; i < rail; i++)
        {
            c_text[col_num + (size / rail) * i] = tmp_text[i][col_num];
        }
    }

    printf("Your cipher : ");
    for (col_num = 0; col_num < size; col_num++)
    {
        printf("%c", c_text[col_num]);
    }
    printf("\n");

    return 0;
}