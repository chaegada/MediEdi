# MediEdi

## 작품개요
메디에디(MediEdi)는 언어의 장벽으로 인해 의료 서비스 접근에 곤란을 겪는 다문화 가정을 대상으로 의약 정보를 제공하는 어플리케이션입니다.
어플리케이션의 주요 기능은 크게 2가지로 의약품 정보 번역 및 병원 위치 출력 서비스입니다.  

의약품 정보 번역 서비스의 경우 검색하고 하는 약품명, 효능, 주의사항, 용법 등을 번역해 제공하고,
병원 위치 출력 서비스의 경우 사용자의 현재 위치 근방의 병원 위치와 함께 진료시간, 전화번호를 제공합니다.
현재 영어, 중국어, 베트남어, 태국어 4가지 언어를 제공하고 있습니다.  

이러한 기능을 통해 의약품에 관한 정보의 미비로 생겨나는 알레르기 및 부작용, 오·남용의 사전예방을 목표로 하며, 의료 서비스의 원활한 이용을 돕습니다.
결과적으로는 다문화 가정의 보건수준 및 삶의 질 향상에 기여하고자 합니다.  

## 1. 작품 소개
### ▸ 기획의도
▹ 행정자치부에 따르면 국내에 거주하는 외국인 주민은 처음 조사를 시행한 2006년에 비해 10년 동안 3배 이상 증가했다. 한국계 중국인은 근로 목적이 많고, 가족 형성 목적으로 거주하는 유형은 베트남 출신이 가장 많다.  

▹ 한국어는 사용 국가 수도 적고, 전 세계적으로 보편적인 언어가 아니다. 하지만 한국의 의약품은 한국어로만 설명이 적혀있고 의약품의 설명은 전문용어로 기술되어 난처한 국내 거주 외국인이 많다.  

▹ 위와 같이 한국어 때문에 의료서비스 접근에 곤란을 겪는 외국인들을 위해서 MediEdi라는 의약품 정보 및 병원 위치 출력 서비스를 제공하고자 한다.  

### ▸ 필요성
▹ 의약품 오남용 감소 : 한국어로만 제공되는 의약품 정보를 번역하여 제공하여 올바르게 이해하고 복용할 수 있도록 도움.
▹ 알레르기 검색 : 개개인의 체질에 따라 복용에 주의해야하는 약품을 강조.  
▹ 병원 검색 : 의약품으로 해결되지 않는 병의 경우에는 신속히 병원을 이용할 수 있도록 병원의 위치와 정보를 제공함.  
▹ 특히, 농어촌의 경우에는 도시에 비해서 병원 접근성이 낮아서 올바른 상비약 복용이 중요함.  

## 2. 작품 내용
|  메뉴  | 설명 |
|:--------:|:--------|
| 의약품 번역 | default 설정 언어 : 영어<br /> 4가지 기능 선택<br /> - 의약품 번역, 병원 위치 찾기, 다문화 정보 제공, 환경설정 |
| 내용 검색 | 바코드 검색이나 이름 검색을 이용해 정보 입력<br /> - 입력 없이 SEARCH 버튼을 누른 경우 토스트를 이용해서 ‘약 이름을 입력하세요’를 설정 언어로 번역해 출력<br />  - 입력 후 SEARCH 버튼을 누른 경우 공공데이터포털의 정보를 기반으로 번역된 정보 출력 |
| 병원 위치 | 공공데이터포털의 병원위치정보를 바탕으로 가까운 병원을 찾을 수 있음<br /> - 말풍선에 병원이름 출력예정<br /> - 말풍선을 클릭하면 병원 상세정보로 넘어감 |
| 환경설정 | 현재 세 가지 환경설정 탭 존재<br /> - 언어 설정 변경<br /> - 강조 형식 변경<br /> - 리스트 강조 목록수정 |
| 언어변경 | 영어, 베트남어, 태국어, 중국어 4개국어를 지원하며 변경 가능 |
| 강조설정 변경 | 단어나 리스트 강조할 때 원하는 방법으로 강조 형식을 선택할 수 있음<br /> - 두껍게, 빨갛게, 기울여서, 밑줄|
| 다문화 정보 링크 | 다누리, 국내 대사관 홈페이지, 외국인을 위한 전자정부 등 다문화 가정 및 외국인에게 유익한 정보 링크 제공 |

## 3. 주요 적용 기술
### ▸ NAVER Papago NMT 번역 api  
▹ 기능 설명 : 네이버에서 제공하는 Papago NMT 번역은 Papago의 인공신경망 기반 기계 번역 기술로 텍스트를 번역한 결과를 반환하는 RESTfulAPI임.  
▹ 선택 이유 : 네이버 파파고는 한국어를 기반으로 번역 언어를 제공함. 따라서 구글 번역보다 한국어 번역이 더 체계화 되어 있다고 느껴짐. 우리의 어플리케이션에서는 한국의약품을 번역하기 때문에 네이버 파파고 기반함.  
▹ 어플리케이션에 띄우는 정보들을 String형의 한국어로 받을 경우, <한국어☞환경설정에서 설정한 언어>로 번역시켜주는 역할을 수행함.  

### ▸ Google 지도 api  
▹ 기능 설명 : 애플리케이션에 google지도를 띄워서 현재위치 및 좌표를 표시해주는 기능  
▹ 선택 이유 : 구글 지도 api의 경우, 전 세계 사람들이 이용하고 있기 때문에 샘플코드가 많아서 활용도가 높다.  
▹ 구글 지도위에 현재위치 및 병원 위치를 출력한 다음, 병원 마커를 선택한 경우에는 병원의 상세정보를 번역하여 출력함.  

### ▸ 공공데이터포털 정보 api  
▹ 식품의약품안전평가원 의약외품 제품 허가정보
의약품 포장의 바코드 번호를 이용해 의약품 데이터 추출
xml 파서를 이용해 해당하는 바코드 번호의 의약품명을 String으로 받아온다.
▹ 병·의원 위치찾기  
병원의 위도, 경도 변수를 받아서 마커를 전국적으로 출력  
OnClickListener를 마커에 적용해서 다음 레이아웃에 정보를 출력후 번역문으로 넘김  

## ▸ 4. dependencies
implementation fileTree(dir: 'libs', include: ['*.jar'])  
implementation 'com.squareup.okhttp3:okhttp:3.5.0'  
implementation 'com.android.support:appcompat-v7:28.0.0-alpha3'  
implementation 'com.android.support.constraint:constraint-layout:1.1.2'  
testImplementation 'junit:junit:4.12'  
androidTestImplementation 'com.android.support.test:runner:1.0.2'  
androidTestImplementation 'com.android.support.test.espresso:espresso-core:3.0.2'  
implementation 'com.google.code.gson:gson:2.8.0'  
implementation 'com.google.android.gms:play-services-maps:15.0.1'  
implementation 'com.google.android.gms:play-services-location:15.0.1'  
implementation 'com.google.android.gms:play-services-places:15.0.1'  
implementation 'com.android.support:support-media-compat:28.0.0'  
implementation 'com.android.support:support-v4:28.0.0'  
implementation 'com.android.support:design:28.0.0'  
implementation 'com.android.support:appcompat-v7:28.0.0'     
implementation 'com.journeyapps:zxing-android-embedded:3.5.0'  
implementation 'net.sourceforge.jexcelapi:jxl:2.6.12'  

## ▸ 5. 출처
### ▸ zxing barcode 출처: https://github.com/journeyapps/zxing-android-embedded

