# 미디어 파일 관리하기

`blank=True` : 해당 필드는 필수 항목이 아니라는 뜻

`blank=True`가 아닌 경우 필드를 입력하지 않고 저장 시 빨간색 테두리로 경고 메시지 뜨는 것 캡쳐해서 붙이기

Pillow 라이브러리 설치 오류

```bash
(venv) λ python manage.py makemigrations
SystemCheckError: System check identified some issues:

ERRORS:
blog.Post.head_image: (fields.E210) Cannot use ImageField because Pillow is not installed.
        HINT: Get Pillow at https://pypi.org/project/Pillow/ or run command "python -m pip install Pillow".
```

## 이미지 업로드 테스트

![image](https://user-images.githubusercontent.com/27791880/127999051-db57e5c8-0e58-4fc7-a7db-6495842379ac.png)

Head image만 볼드체로 되어 있지 않다. 즉, `blank=True`로 인해 필수가 아니라는 뜻.

![image](https://user-images.githubusercontent.com/27791880/127998868-461a9eae-a4fe-407b-a28a-72dd7ccc79ca.png)

파일 선택 후 이미지 업로드 시 프로젝트 폴더 밑에 `_media`폴더가 생성됨.

그러나 이미지 클릭 시 오류 발생

![image](https://user-images.githubusercontent.com/27791880/127999832-633d8264-85bd-4c60-a590-1db36fc8f153.png)

http request를 받으면 제일 먼저 urls.py를 만나게 되는데 여기서 media URL에 대한 설정이 안되어 있기 때문에 발생.

![image](https://user-images.githubusercontent.com/27791880/128003306-aff51305-e467-4691-8fa3-a2bccc994deb.png)

p.216에서 나오는 에러 메시지
