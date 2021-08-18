# CH20

## p.568 오류와 관련하여

```bash
D:\JaeSeok\Github\do_it_django (main -> origin)
(venv) λ pip install -U pip
Requirement already satisfied: pip in d:\jaeseok\github\do_it_django\venv\lib\site-packages (21.2.3)
Collecting pip
  Downloading pip-21.2.4-py3-none-any.whl (1.6 MB)
     |████████████████████████████████| 1.6 MB 1.3 MB/s
Installing collected packages: pip
  Attempting uninstall: pip
    Found existing installation: pip 21.2.3
    Uninstalling pip-21.2.3:
ERROR: Could not install packages due to an OSError: [WinError 5] 액세스가 거부되었습니다: 'd:\\jaeseok\\github\\do_it_django\\venv\\scripts\\pip.exe'
Check the permissions.
```

![image](https://user-images.githubusercontent.com/27791880/129351030-070966f0-e93a-4e6b-913e-bf8a1bcfcb90.png)

pip를 업데이트하려다가 위와 같은 문제를 겪었다. pip가 삭제된 상황이다. 어떻게 재설치할까?

```bash
D:\JaeSeok\Github\do_it_django (main -> origin)                                   
(venv) λ curl https://bootstrap.pypa.io/get-pip.py -o get-pip.py                  
  % Total    % Received % Xferd  Average Speed   Time    Time     Time  Current   
                                 Dload  Upload   Total   Spent    Left  Speed     
100 1911k  100 1911k    0     0  1911k      0  0:00:01  0:00:01 --:--:--  962k    
```

![image](https://user-images.githubusercontent.com/27791880/129351204-7875d471-65bd-405b-86ab-d1a474513a5a.png)

위와 같은 명령어를 사용해서 다운로드 받은 뒤

```bash
D:\JaeSeok\Github\do_it_django (main -> origin)                                                   
(venv) λ python get-pip.py                                                                        
WARNING: Ignoring invalid distribution -ip (d:\jaeseok\github\do_it_django\venv\lib\site-packages)
WARNING: Ignoring invalid distribution -ip (d:\jaeseok\github\do_it_django\venv\lib\site-packages)
Collecting pip                                                                                    
  Using cached pip-21.2.4-py3-none-any.whl (1.6 MB)                                               
Collecting wheel                                                                                  
  Downloading wheel-0.37.0-py2.py3-none-any.whl (35 kB)                                           
WARNING: Ignoring invalid distribution -ip (d:\jaeseok\github\do_it_django\venv\lib\site-packages)
Installing collected packages: wheel, pip                                                         
WARNING: Ignoring invalid distribution -ip (d:\jaeseok\github\do_it_django\venv\lib\site-packages)
WARNING: Ignoring invalid distribution -ip (d:\jaeseok\github\do_it_django\venv\lib\site-packages)
Successfully installed pip-21.2.4 wheel-0.37.0                                                    
WARNING: Ignoring invalid distribution -ip (d:\jaeseok\github\do_it_django\venv\lib\site-packages)
WARNING: Ignoring invalid distribution -ip (d:\jaeseok\github\do_it_django\venv\lib\site-packages)
WARNING: Ignoring invalid distribution -ip (d:\jaeseok\github\do_it_django\venv\lib\site-packages)
```

위와 같은 명령어를 추가로 입력하면 설치된다. WARNING 관련된 부분은 이미 설치되어서 나오는 것 같다.(추가 확인 필요)

```
D:\JaeSeok\Github\do_it_django (main -> origin)
(venv) λ pip -V
pip 21.2.4 from D:\JaeSeok\Github\do_it_django\venv\lib\site-packages\pip (python 3.9)
```

정상 설치 완료

![image](https://user-images.githubusercontent.com/27791880/129351468-c552e191-ba8f-4929-8dda-ce42bd0dc3be.png)

## WARNING: Ignoring invalid distribution -ip 해결하기

pip를 재설치하긴 했는데, 업데이트 도중 충돌이 발생해서 그런지 tidle이 붙은 파일들이 몇 개 있었다.

![image](https://user-images.githubusercontent.com/27791880/129356468-15f6ac87-3299-4655-9061-465a501403fe.png)

tidle이 붙은 2개의 파일을 삭제하고 보니 해결되었다.

![image](https://user-images.githubusercontent.com/27791880/129356720-393b2554-bb70-4dc7-a764-4646f657bdf8.png)

삭제한 파일

* `~ip`
* `~ip-21.2.3.dist-info`

## 터미널에서 제안하는 올바른 upgrade 명령

터미널에서는 만약 나의 pip가 구버전이라면 upgrade를 제안하는데, 이 때 터미널에서 제안하는 방법을 따른다면 위와 같은 문제가 발생하지 않는다.

```bash
c:\users\jaeseok\appdata\local\programs\python\python39\python.exe -m pip install --upgrade pip
```

## p.574 오류

`docker-compose up`을 입력하면 여러 문장들이 길게 출력되는데 가장 밑에 있는 메시지만 확인해보자.

```bash
web_1  |   File "/usr/local/lib/python3.8/site-packages/django/db/backends/postgresql/base.py", line 29, in <module>
web_1  |     raise ImproperlyConfigured("Error loading psycopg2 module: %s" % e)
web_1  | django.core.exceptions.ImproperlyConfigured: Error loading psycopg2 module: No module named 'psycopg2'
```

## p.575 ProgrammingError

![image](https://user-images.githubusercontent.com/27791880/129439609-4a5ef7f5-96b6-4ff1-929c-d8d41142c5a5.png)

## p.583

장고가 정적 파일을 찾지 못해 발생한 오류
![image](https://user-images.githubusercontent.com/27791880/129444966-e5a682f2-489c-4fe7-87fd-e461eb082960.png)

시크릿창에서 오픈
![image](https://user-images.githubusercontent.com/27791880/129445011-ee49f505-6175-4512-b38f-11faa8f72d20.png)
