## Create post
Creates and returns a new post object. File id is generated by the database and stored.

``
POST /post/{boardId}
``

Name | Type | In | Description
------------ | ------------- | ------------- | -------------
boardId | String | path |

### Code Sample

#### Send
```
POST ../api/v1/post/RiT0tkKMQq67MCuiuR92nQ
```
```json
{
  "author": "WNgahiw4Re-KXY-uNPoD5A",
  "path": "/path/",
  "text": "new post text"
}
```
#### Response
```
200 OK
```
```json
{
  "author": "WNgahiw4Re-KXY-uNPoD5A",
  "path": "/path/",
  "text": {
    "dateModified": "2021-05-11T12:41:27.5990822",
    "text": "new post text"
  },
  "date-created": "2021-05-11T12:41:27.5990822",
  "comments": [],
  "id": "9N_VjqehQ5uokrdxyGt3ZA"
}
```

## Get posts by board ID
Provides all posts in a board given the board's ID.

``
GET /post/{boardId}
``

Name | Type | In | Description
------------ | ------------- | ------------- | -------------
boardId | String | path |

### Code Sample

#### Send
```
GET ../api/v1/post/RiT0tkKMQq67MCuiuR92nQ
```
```json
```
#### Response
```
200 OK
```
```json
[
  {
    "author": "WNgahiw4Re-KXY-uNPoD5A",
    "path": "/",
    "text": {
      "dateModified": "2021-05-11T12:39:20.5217388",
      "text": "Hello world"
    },
    "date-created": "2021-05-11T12:39:20.5207375",
    "comments": [
      "6wjzs0IyQMSucbwnFDs5PQ",
      "-wsocC7-RtCcGHdzciDO8Q"
    ],
    "id": "Obq9XnaoQ5uoa_sCysIkpg"
  },
  {
    "author": "WNgahiw4Re-KXY-uNPoD5A",
    "path": "/",
    "text": {
      "dateModified": "2021-05-11T12:39:20.5217388",
      "text": "Lorem ipsum dolor sit amet, consectetur adipiscing elit"
    },
    "date-created": "2021-05-11T12:39:20.5217388",
    "comments": [
      "6Jo1FNlpQsmOdjrd1qukzw",
      "d0xRxQYLR_iG6FD2nQk5SA",
      "vp5R_I3zRdam0uFi6l5DRw"
    ],
    "id": "yudAPlaHSaakf5YJPABivA"
  },
  {
    "author": "WNgahiw4Re-KXY-uNPoD5A",
    "path": "/path/",
    "text": {
      "dateModified": "2021-05-11T12:41:27.5990822",
      "text": "new post text"
    },
    "date-created": "2021-05-11T12:41:27.5990822",
    "comments": [],
    "id": "9N_VjqehQ5uokrdxyGt3ZA"
  }
]
```

## Get post by ID
Provides available information about a post given the ID.

``
GET /post/{boardId}/{postId}
``

Name | Type | In | Description
------------ | ------------- | ------------- | -------------
boardId | String | path |
postId | String | path |

### Code Sample

#### Send
```
GET ../api/v1/post/RiT0tkKMQq67MCuiuR92nQ/Obq9XnaoQ5uoa_sCysIkpg
```
```json
```
#### Response
```
200 OK
```
```json
{
  "author": "WNgahiw4Re-KXY-uNPoD5A",
  "path": "/",
  "text": {
    "dateModified": "2021-05-11T12:39:20.5217388",
    "text": "Hello world"
  },
  "date-created": "2021-05-11T12:39:20.5207375",
  "comments": [
    "6wjzs0IyQMSucbwnFDs5PQ",
    "-wsocC7-RtCcGHdzciDO8Q"
  ],
  "id": "Obq9XnaoQ5uoa_sCysIkpg"
}
```

## Get post author by ID
Provides the post's author ID information given the post ID.

``
GET /post/{boardId}/{postId}/author
``

Name | Type | In | Description
------------ | ------------- | ------------- | -------------
boardId | String | path |
postId | String | path |

### Code Sample

#### Send
```
GET ../api/v1/post/RiT0tkKMQq67MCuiuR92nQ/Obq9XnaoQ5uoa_sCysIkpg/author
```
```json
```
#### Response
```
200 OK
```
```json
WNgahiw4Re-KXY-uNPoD5A
```

## Get post date created by ID
Provides when the post was created given the post ID.

``
GET /post/{boardId}/{postId}/date-created
``

Name | Type | In | Description
------------ | ------------- | ------------- | -------------
boardId | String | path |
postId | String | path |

### Code Sample

#### Send
```
GET ../api/v1/post/RiT0tkKMQq67MCuiuR92nQ/Obq9XnaoQ5uoa_sCysIkpg/date-created
```
```json
```
#### Response
```
200 OK
```
```json
"2021-05-11T12:39:20.5207375"
```

## Get post date modified by ID
Provides when the post was last modified given the post ID.

``
GET /post/{boardId}/{postId}/date-modified
``

``
GET /post/{boardId}/{postId}/text/date-modified
``

Name | Type | In | Description
------------ | ------------- | ------------- | -------------
boardId | String | path |
postId | String | path |

### Code Sample

#### Send
```
GET ../api/v1/post/RiT0tkKMQq67MCuiuR92nQ/Obq9XnaoQ5uoa_sCysIkpg/date-modified
```
```json
```
#### Response
```
200 OK
```
```json
"2021-05-11T12:39:20.5217388"
```

## Get post text by ID
Provides the post text given the post ID.

``
GET /post/{boardId}/{postId}/text
``

``
GET /post/{boardId}/{postId}/text/text
``

Name | Type | In | Description
------------ | ------------- | ------------- | -------------
boardId | String | path |
postId | String | path |

### Code Sample

#### Send
```
GET ../api/v1/post/RiT0tkKMQq67MCuiuR92nQ/Obq9XnaoQ5uoa_sCysIkpg/text
```
```json
```
#### Response
```
200 OK
```
```json
Hello world
```

## Update post text by ID
Updates a post's text in the database given the id.

``
PUT /post/{boardId}/{postId}
``

Name | Type | In | Description
------------ | ------------- | ------------- | -------------
boardId | String | path |
postId | String | path |

### Code Sample

#### Send
```
PUT ../api/v1/post/RiT0tkKMQq67MCuiuR92nQ/Obq9XnaoQ5uoa_sCysIkpg
```
```json
{
    "text": "updated text"
}
```
#### Response
```
200 OK
```
```json
{
  "author": "WNgahiw4Re-KXY-uNPoD5A",
  "path": "/",
  "text": {
    "dateModified": "2021-05-11T13:28:22.0868379",
    "text": "updated text"
  },
  "date-created": "2021-05-11T12:39:20.5207375",
  "comments": [
    "6wjzs0IyQMSucbwnFDs5PQ",
    "-wsocC7-RtCcGHdzciDO8Q"
  ],
  "id": "Obq9XnaoQ5uoa_sCysIkpg"
}
```

## Delete post by ID
Deletes a post from a board given the post's id.

``
DELETE /post/{boardId}/{postId}
``

Name | Type | In | Description
------------ | ------------- | ------------- | -------------
boardId | String | path |
postId | String | path |

### Code Sample

#### Send
```
DELETE ../api/v1/post/RiT0tkKMQq67MCuiuR92nQ/Obq9XnaoQ5uoa_sCysIkpg
```
```json
```
#### Response
```
200 OK
```
```json
```