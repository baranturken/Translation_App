from flask import Flask, request, jsonify
from googletrans import Translator
import mysql.connector
from mysql.connector import Error

app = Flask(__name__)

def create_connection():
    return mysql.connector.connect(
        host='127.0.0.1',
        database='translations',
        user='root',
        password='')  # Replace with your MySQL root password if it exists

def insert_translation(source_text, translated_text, source_lang, target_lang):
    try:
        connection = create_connection()
        cursor = connection.cursor()
        sql_insert_query = """INSERT INTO translations (source_text, translated_text, source_language, target_language)
                              VALUES (%s, %s, %s, %s)"""
        cursor.execute(sql_insert_query, (source_text, translated_text, source_lang, target_lang))
        connection.commit()
        cursor.close()
        connection.close()
    except Error as e:
        print("Error while connecting to MySQL", e)

@app.route('/translate', methods=['POST'])
def translate_text():
    data = request.get_json()
    text_to_translate = data['text']
    src_language = data['source_lang']
    dest_language = data['dest_lang']

    translator = Translator()
    translation = translator.translate(text_to_translate, src=src_language, dest=dest_language)
    translated_text = translation.text

    # Insert the translation result into the database
    insert_translation(text_to_translate, translated_text, src_language, dest_language)

    return jsonify({'translated_text': translated_text})

if __name__ == "__main__":
    app.run(debug=True)