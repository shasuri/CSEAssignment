#include <stdio.h>
#include <string.h>
#include <stdbool.h>

#define MAX_LEN 500
#define ALPHABET 26

struct Text
{
    char content[MAX_LEN];
    int size;
};

struct KeyPair
{
    int first;
    int second;
};

struct Text alffineEncrypt(struct Text, struct KeyPair);
bool isCapital(char);
bool isSmall(char);
char alffineEncryptCapital(char, struct KeyPair);
char alffineEncryptSmall(char, struct KeyPair);
int charConvertInt(char, char);
char charRecoverInt(char, char);
int alffineConvert(int, struct KeyPair);
char alffineConvertChar(char, struct KeyPair, char);

int main(void)
{
    struct Text plainText, cipherText;
    struct KeyPair alffineKey;

    printf("Your plain text : ");
    scanf("%[^\n]s", plainText.content);
    plainText.size = strlen(plainText.content);

    printf("Your first key : ");
    scanf("%d", &(alffineKey.first));

    printf("Your second key : ");
    scanf("%d", &(alffineKey.second));

    cipherText = alffineEncrypt(plainText, alffineKey);

    printf("Your cipher text : %s\n",cipherText.content);
}

struct Text alffineEncrypt(struct Text p, struct KeyPair k)
{
    struct Text cipherText;
    for (int i = 0; i < p.size; i++)
    {
        char pre = p.content[i];
        if (isCapital(pre))
        {
            cipherText.content[i] = alffineEncryptCapital(pre,k);
        }
        else if (isSmall(pre))
        {
            cipherText.content[i] = alffineEncryptSmall(pre,k);
        }
        else
            cipherText.content[i] = pre;
    }
    cipherText.content[p.size] = '\0';
    cipherText.size = strlen(cipherText.content);

    return cipherText;
}

char alffineEncryptCapital(char c, struct KeyPair k)
{
    alffineConvertChar(c, k, 'A');
}

char alffineEncryptSmall(char c, struct KeyPair k)
{
    alffineConvertChar(c, k, 'a');
}

int charConvertInt(char plainChar, char baseChar)
{
    return (plainChar - baseChar);
}

char charRecoverInt(char plainChar, char baseChar)
{
    return (plainChar + baseChar);
}

int alffineConvert(int plainChar, struct KeyPair keys)
{
    return (plainChar * keys.first + keys.second) % ALPHABET;
}

char alffineConvertChar(char plainChar, struct KeyPair keys, char baseChar)
{
    int convertedChar = charConvertInt(plainChar, baseChar);
    int encryptedPlainInt = alffineConvert(convertedChar, keys);
    char recoveredChar = charRecoverInt(encryptedPlainInt, baseChar);
}

bool isCapital(char c)
{
    return (c >= 'A') && (c <= 'Z');
}

bool isSmall(char c)
{
    return (c >= 'a') && (c <= 'z');
}
