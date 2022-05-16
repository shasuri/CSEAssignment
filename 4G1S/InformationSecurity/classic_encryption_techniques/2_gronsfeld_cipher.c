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

struct Key
{
    int key[MAX_LEN];
    int size;
};

struct Key convertStringToKey(char[MAX_LEN]);
struct Text gronfeldEncrypt(struct Text, struct Key);
char gronfeldEncryptCapital(char, int);
char gronfeldEncryptSmall(char, int);
int charConvertInt(char, char);
char charRecoverInt(char, char);
char gronfeldConvertChar(char, int, char);
int gronfeldConvert(int, int);
bool isCapital(char);
bool isSmall(char);
bool isNumber(char);

int main(void)
{
    struct Text plainText, cipherText;
    struct Key gronfeldKey;
    char keyString[MAX_LEN];

    printf("Your plain text : ");
    scanf("%[^\n]s", plainText.content);
    plainText.size = strlen(plainText.content);

    printf("Your key : ");
    scanf("%s", keyString);
    gronfeldKey = convertStringToKey(keyString);

    cipherText = gronfeldEncrypt(plainText, gronfeldKey);

    printf("Your cipher text : %s\n", cipherText.content);
}

struct Key convertStringToKey(char s[MAX_LEN])
{
    struct Key convertedKey;
    convertedKey.size = strlen(s);

    for (int i = 0; i < convertedKey.size; i++)
    {

        if (isNumber(s[i]))
        {
            convertedKey.key[i] = charConvertInt(s[i], '0');
        }
        else
        {
            convertedKey.key[i] = s[i];
        }
    }

    return convertedKey;
}

struct Text gronfeldEncrypt(struct Text p, struct Key k)
{
    struct Text cipherText;
    
    for (int i = 0; i < p.size; i++)
    {
        char preChar = p.content[i];
        int preKey = k.key[i%k.size];

        if (isCapital(preChar))
        {
            cipherText.content[i] = gronfeldEncryptCapital(preChar, preKey);
        }
        else if (isSmall(preChar))
        {
            cipherText.content[i] = gronfeldEncryptSmall(preChar, preKey);
        }
        else
            cipherText.content[i] = preChar;
    }
    cipherText.content[p.size] = '\0';
    cipherText.size = strlen(cipherText.content);

    return cipherText;
}

char gronfeldEncryptCapital(char c, int k)
{
    gronfeldConvertChar(c, k, 'A');
}

char gronfeldEncryptSmall(char c, int k)
{
    gronfeldConvertChar(c, k, 'a');
}

int charConvertInt(char plainChar, char baseChar)
{
    return (int)(plainChar - baseChar);
}

char charRecoverInt(char plainChar, char baseChar)
{
    return (plainChar + baseChar);
}

char gronfeldConvertChar(char plainChar, int key, char baseChar)
{
    int convertedChar = charConvertInt(plainChar, baseChar);
    int encryptedPlainInt = gronfeldConvert(convertedChar, key);
    char recoveredChar = charRecoverInt(encryptedPlainInt, baseChar);
}

int gronfeldConvert(int plainChar, int key)
{
    return (plainChar + key) % ALPHABET;
}

bool isCapital(char c)
{
    return (c >= 'A') && (c <= 'Z');
}

bool isSmall(char c)
{
    return (c >= 'a') && (c <= 'z');
}

bool isNumber(char c)
{
    return (c >= '0') && (c <= '9');
}