FROM python:3.6

RUN pip3 install pymysql &&\
    pip3 install cryptography

COPY ./main.py /

CMD ["python3","main.py"]