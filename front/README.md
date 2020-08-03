# frontend

### 개발환경

- Chrome
- Git
- VS code
- Vue Dev Tools



### 사용할 Extension

- Prettier - 단 받고나서 비활성화 할것
- Eslint
- Vuter
- Vue VScode Snippet
- Auto Complete Tag



### 사용할 라이브러리

- vue

- vuex
- vue router
- axios
- [toast](https://ui.toast.com/tui-editor/)



### Commit Message 규칙

>  `Jira이슈번호 | 메세지`



### SCSS 사용

[SCSS 사용법](https://sass-lang.com/guide)



### Package.json Install

> 클론이나 포크 받고 난 후 `npm i` 또는 `npm install`로 모든 패키지를 설치해준다



### Vscode settings

- settings.json에 추가

  ```json
  {
      ...
    "eslint.autoFixOnSave": true,
    "eslint.alwaysShowStatus": true,
    // ESLint
    "eslint.validate": [
      {
        "language": "vue",
        "autoFix": true
      },
      {
        "language": "javascript",
        "autoFix": true
      },
      {
        "language": "javascriptreact",
        "autoFix": true
      },
      {
        "language": "typescript",
        "autoFix": true
      },
      {
        "language": "typescriptreact",
        "autoFix": true
      }
    ],
    "editor.codeActionsOnSave": {
      "source.fixAll.eslint": true
    },
    // don't format on save
    "editor.formatOnSave": false
  }
  ```




### ESLINT에서 사용할 prettier 규칙 설정

```js
    "prettier/prettier": ["error", {
      singleQuote: true,
      semi: true,
      useTabs: true,
      tabWidth: 2,
      trailingComma: 'all',
      printWidth: 80,
      bracketSpacing: true,
      arrowParens: 'avoid',
    }]
```



### 

