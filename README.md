# oEmbed에 대한 설명입니다.
환경
- java8
- ajax
- jsp
- springboot 2.7.5

json response data oEmbed에 등록되어있는 provider들을 조회한 데이터를 요청하여 dto로 변경하여 providers.endpoint.schemes에서 요청 url과 맞는 포맷이 있을 경우 oembed data를 요청하는 방식으로 구현

결과적으로 oembed 데이터는 map 형식으로 가져와 양식이 다른 response값을 받더라도 화면에 표출 될 수 있도록 하였습니다.(.yml 파일을 활용하지 않는 방식으로 구현하였습니다.)

이외에 twitter, youtube, vimep의 oEmbed는 성공적으로 호출 되었습니다.

instagram에서 oEmbed기능을 인증형으로 변경한 이유를 검색해본 결과 다음과 같았습니다.
![image](https://user-images.githubusercontent.com/49673268/204096949-14a10493-4c64-4a93-9df1-085212367c99.png)
youtube

![image](https://user-images.githubusercontent.com/49673268/204097050-5a33f208-f36d-4834-b81d-0d1abb790d6a.png)
twitter

![image](https://user-images.githubusercontent.com/49673268/204097091-843de612-ff72-4c71-95ee-6d1edbf0f61c.png)
youtube

* 인스타그램 oEmbed는 호출이 되지 않는 이유*
instagram은 oEmbed api를 개방형 api에서 따로 인증된 사용자만 호출 할 수 있는 인증형 api로 정책을 바꾸어 호출이 되지 않았으며 호출을 하였을 경우에도 403 권한 거절 응답을 보내옵니다.

=> Exception=403 Forbidden: "{"error":{"message":"(#200) Provide valid app ID","type":"OAuthException","code":200,"fbtrace_id":"ArMilGfl7o_9YOg1xfk3qxF"}}"

1.지적재산권 침해 리스크를 낮추기 => 인스타그램 가입 시 피드 공유를 허용했기 때문에 다른 사람이 인스타그램 콘텐츠를 oEmbed로 퍼가는 것은 지적재산권 침해가 아니라는 판결이 있지만 향후 어떻게 변할지 모릅니다. 때문에 oEmbed 공유를 개방형 API를 통해서 불특정 다수 누구나 할 수 있는 것이 아니라 페이스북에서 검토해 인증 받은 사람만 oEmbed 공유가 가능토록 만들어 사용자가 더 많은 책임을 지도록 만들려는 의도

2.개방형 API보다는 공유를 제한하는 방식으로 변경함으로써 데이타를 모으고 분석하는데 훨씬 더 용이하게 만들려는 의도가 있을 것으로 보입니다. 누구나 그냥 copy & past해서 콘텐츠가 공유되면 공유 시점과 공유 결과를 파악하는데 아무래도 한계가 있을 것 입니다. 만약 인증된 사용자만 피드를 공유할 수 있다면 데이타 분석이 보다 명확해지고 타겟 설정이 용이

*기존 개방형 api 방식처럼 instagram oEmbed를 활용하기 위해서는 다음과 같은 순서를 거쳐야합니다.*
1. 페이스북 개발자 계정(developers.facebook.com에서 계정 생성 가능)
2. 페이스북 앱 등록(앱 ID 생성)
3. 앱 권한 및 기능에 Oembed Read 제품 추가
4. 액세스 토큰 생성
5. Facebook 앱 라이브 시키기
6. 페이스북 앱 승인 신청 어느 순간부터 앱 승인 신청단계가 추가됨



*페이스북 개발자 계정없이 해결 방법*
1. Jetpack을 사용
2. Smash Balloon 플러그인

