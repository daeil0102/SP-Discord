<h1>SP-Discord</h1>

<p>해당 프로젝트는 프록시용 서버에서 디스코드 봇 연동하는 프로젝트 입니다</p>
<p>해당 플러그인을 사용하기 위해선 SP-Framework가 필요합니다</p>
<p>해당 프로젝트는 미완성된 프로젝트 입니다 일부 오류가 있을 수 있습니다</p>
<p>추후 단일서버도 지원 예정입니다</p>

<h2>플러그인 버전</h2>

- 플러그인 : spigot 1.12+
    - 1.12.x~1.16.x -all 버전 사용
    - 1.17+ -base
- 벨로시티 : 3.4.0-SNAPSHOT
    - all 버전 사용

<h2>라이센스</h2>

Copyright (c) 2026 Teujaem

1. 상업적 이용이 가능합니다.
2. 2차 수정이 불가능 합니다. (fork 포함)
3. 2차 배포가 불가능 합니다.

<h2>디스코드</h2>
https://discord.gg/yAGw7CmFFK

<h2>config</h2>

<p>velocity/plugins/spdiscord/config.yml</p>

```
token: "token"
serverId: "serverId"
consoleChannelId: "consoleChannelId"
chatChannelId: "chatChannelId"
```

<p>bukkit/plugins/SP-Discord/config.yml</p>

```
# 해당 서버를 표시할 이름
# 작성 안할 시 폴더이름 작성됨
name: "server name"
```