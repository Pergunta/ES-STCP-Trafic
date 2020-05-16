# pull official base image
FROM node:13.12.0-alpine as build

# set working directory
WORKDIR /app

# add /app/node_modules/.bin to $PATH
ENV PATH /app/node_modules/.bin:$PATH

# install app dependencies
COPY package.json ./

RUN npm install --silent

# add app
COPY . /app
RUN npm run build

## STAGE 2: Production Environment ###
FROM nginx:stable-alpine
COPY nginx/nginx.conf /etc/nginx/nginx.conf
COPY --from=build /app/build /var/www

EXPOSE 80
CMD ["nginx", "-g", "daemon off;"]