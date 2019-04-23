FROM python:2.7-stretch

# Alternatively use ADD https:// (which will not be cached by Docker builder)
ADD https://github.com/alexellis/faas/releases/download/0.5.8-alpha/fwatchdog /usr/bin/fwatchdog
RUN chmod +x /usr/bin/fwatchdog

WORKDIR /root/

COPY handler.py           .
COPY requirements.txt   .
RUN pip install -r requirements.txt

ENV fprocess="python handler.py"

HEALTHCHECK --interval=1s CMD [ -e /tmp/.lock ] || exit 1

CMD ["/usr/bin/fwatchdog"]
