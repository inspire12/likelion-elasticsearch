ARG ELK_VERSION
FROM docker.elastic.co/elasticsearch/elasticsearch:${ELK_VERSION}
# nori 형태소 분석기 플러그인 설치
RUN elasticsearch-plugin install analysis-nori --batch