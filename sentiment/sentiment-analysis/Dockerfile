FROM python:2.7-alpine

RUN pip install textblob && \
    python -m textblob.download_corpora

WORKDIR /root/

COPY handler.py .


CMD ["python","handler.py"]

